package com.zfinance.controller.payment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.zfinance.dto.request.PaginationRequestOptions;
import com.zfinance.dto.request.payment.PaymentFilter;
import com.zfinance.dto.request.payment.PaymentRecord;
import com.zfinance.dto.request.payment.PaymentSort;
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.mapper.PaymentMapper;
import com.zfinance.orm.payment.Payment;
import com.zfinance.parser.CsvParser;
import com.zfinance.parser.ExcelParser;
import com.zfinance.parser.XmlParser;
import com.zfinance.services.payment.PaymentService;

@RestController
@RequestMapping("/merchant-payment")
@CrossOrigin("*")
public class PaymentController {
	
	@Autowired
	private ExcelParser excelParser;
	
	@Autowired
	private CsvParser csvParser;

    @Autowired
    private XmlParser xmlParser;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public PaginationResponse<PaymentRecord> getRecords(
			@RequestBody PaginationRequestOptions<PaymentFilter, PaymentSort> data) {

		List<Payment> payments = paymentService.serachPayments(data.getFilter(), data.getSort());

		PaginationResponse<PaymentRecord> paginationResponse = new PaginationResponse<>();
		paginationResponse.setRecords(PaymentMapper.INSTANCE.mapPayments(payments));
		paginationResponse.setTotalRecords(payments.size());

		paginationResponse.setPageSize(data.getPageSize() != null ? Integer.valueOf(data.getPageSize()) : null);
		paginationResponse.setPageNumber(data.getPageNumber() != null ? Integer.valueOf(data.getPageNumber()) : null);

		return paginationResponse;
	}
	
	@GetMapping
	public List<PaymentRecord> getAllPayments() {
		return PaymentMapper.INSTANCE.mapPayments(paymentService.getPayments());
	}
	
	@GetMapping("/{id}")
	public PaymentRecord getPaymentById(@PathVariable String id) {
		return PaymentMapper.INSTANCE.mapPayment(paymentService.getPaymentById(id));
	}
	
	@PostMapping("/create")
	public PaymentRecord savePayment(@RequestBody PaymentRecord payment) {
		return PaymentMapper.INSTANCE.mapPayment(paymentService.savePayment(PaymentMapper.INSTANCE.mapPaymentRecord(payment)));
	}
	
	@PostMapping("/createAll")
	public List<PaymentRecord> savePayment(@RequestBody List<PaymentRecord> payments) {
		return PaymentMapper.INSTANCE.mapPayments(paymentService.savePayments(PaymentMapper.INSTANCE.mapPaymentRecords(payments)));
	}
	
	@PutMapping("/{paymentId}")
	public PaymentRecord cancelPayment(@PathVariable String PaymentId) {
		return PaymentMapper.INSTANCE.mapPayment(paymentService.cancelPayment(PaymentId));
	}
	
	@PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Uploaded file is empty");
        }

        try {
            String fileExtension = getFileExtension(file.getOriginalFilename());
            switch (fileExtension.toLowerCase()) {
                case "csv":
                    return handleCsvUpload(file.getInputStream());
                case "xml":
                    return handleXmlUpload(file.getInputStream());
                case "xls":
                case "xlsx":
                    return handleExcelUpload(file.getInputStream());
                default:
                    return ResponseEntity.badRequest().body("Unsupported file type");
            }
        } catch (IOException | JAXBException | EncryptedDocumentException | CsvException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading or processing file.");
        }
    }
	
	
	
    public ResponseEntity<String> handleXmlUpload(InputStream inputStream) throws JAXBException, IOException {
        
        List<PaymentRecord> payments = xmlParser.parse(inputStream);
        
        paymentService.savePayments(PaymentMapper.INSTANCE.mapPaymentRecords(payments));
        
        return ResponseEntity.ok("File uploaded successfully.");
    }
	
    public ResponseEntity<String> handleCsvUpload(InputStream inputStream) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> rows = reader.readAll();
            List<PaymentRecord> paymentRecords = csvParser.parseCsvRows(rows);

            paymentService.savePayments(PaymentMapper.INSTANCE.mapPaymentRecords(paymentRecords));
            
            return ResponseEntity.ok("CSV file uploaded and processed successfully.");
        }
    }
    
    private ResponseEntity<String> handleExcelUpload(InputStream inputStream) throws IOException, EncryptedDocumentException, InvalidFormatException {
        List<PaymentRecord> paymentRecords = excelParser.parse(inputStream);
        
        paymentService.savePayments(PaymentMapper.INSTANCE.mapPaymentRecords(paymentRecords));

        return ResponseEntity.ok("Excel file uploaded and processed successfully.");
        
    }
	
	private String getFileExtension(String filename) {
        if (filename != null && filename.lastIndexOf(".") != -1) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        return "";
    }
}

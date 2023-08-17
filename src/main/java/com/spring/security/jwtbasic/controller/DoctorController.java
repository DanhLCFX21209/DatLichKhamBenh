package com.spring.security.jwtbasic.controller;

import com.spring.security.jwtbasic.entity.*;
import com.spring.security.jwtbasic.service.PatientService;
import com.spring.security.jwtbasic.service.ScheduleService;
import com.spring.security.jwtbasic.service.StatusService;
import com.spring.security.jwtbasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    //5.2.2. Hiển thị danh sách bệnh nhân
    @GetMapping("/patients")
    public ResponseEntity<ApiResponse> getPatients() {
        List<Patient> patients = patientService.getPatients();
        if (patients.isEmpty()) {
            ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT.value(), "Không tìm thấy bệnh nhân.", null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Danh sách bệnh nhân.", patients);
            return ResponseEntity.ok(response);
        }
    }

    //5.2.3. Nhận lịch khám của bệnh nhân
    @PutMapping("/patients/{patientId}/lock")
    public ResponseEntity<ApiResponse> lock(@PathVariable("patientId") int patientId, Authentication authentication) {
        try {
            String email = authentication.getName();
            User doctor = userService.findByEmail(email);
            Patient patient = patientService.findById(patientId);
            if (patient == null) {
                // If the patient with the given ID is not found, return a 404 Not Found status code.
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bệnh nhân với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if (doctor.getId() != patient.getDoctor().getId()) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            } else {
                Status status = statusService.findStatusByName("Active");
                patient.setDescription(null);
                patient.setStatus(status);
                patientService.savePatient(patient);
                // If the patient is successfully locked, return a 200 OK status code.
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Khóa bệnh nhân thành công.", null);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // If there is an exception, return a 500 Internal Server Error status code.
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.2.3. Nhận/hủy lịch khám của bệnh nhân
    @PutMapping("/patients/{patientId}/unlock")
    public ResponseEntity<ApiResponse> unlock(@PathVariable("patientId") int patientId, @RequestBody Map<String, String> request,Authentication authentication) {
        try {
            String email = authentication.getName();
            User doctor = userService.findByEmail(email);
            Patient patient = patientService.findById(patientId);
            if (patient == null) {
                // If the patient with the given ID is not found, return a 404 Not Found status code.
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bệnh nhân với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

            } else if (doctor.getId() != patient.getDoctor().getId()) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            } else {
                Status status = statusService.findStatusByName("Inactive");
                patient.setStatus(status);
                String description = request.get("description");
                patient.setDescription(description);
                patientService.savePatient(patient);
                // If the patient is successfully unlocked, return a 200 OK status code.
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Mở khóa bệnh nhân thành công.", null);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // If there is an exception, return a 500 Internal Server Error status code.
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}

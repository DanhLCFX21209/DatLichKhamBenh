package com.spring.security.jwtbasic.controller;

import com.spring.security.jwtbasic.entity.*;
import com.spring.security.jwtbasic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private DoctorUserService doctorUserService;

    //5.3.2. Khóa tài khoản của bệnh nhân
    @PutMapping("/patients/{patientId}/lock")
    public ResponseEntity<ApiResponse> lockPatient(@PathVariable("patientId") int patientId) {
        try {
            User user = userService.findById(patientId);
            if (user == null) {
                // Nếu không tìm thấy bệnh nhân với ID đã cho, trả về 404 Not Found
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bệnh nhân với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if ( user.getRole().getId() != 1) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            // Lock bệnh nhân bằng cách đặt mô tả (description) thành null và isActive thành "1"
            user.setDescription(null);
            user.setIsActive("1");
            userService.saveUser(user);

            // Trả về 200 OK và ApiResponse thông báo thành công
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Khóa bệnh nhân thành công.", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Nếu có ngoại lệ xảy ra trong quá trình xử lý, trả về 500 Internal Server Error
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.3.2. Hủy khóa tài khoản của bệnh nhân
    @PutMapping("/patients/{patientId}/unlock")
    public ResponseEntity<ApiResponse> unlockPatient(@PathVariable("patientId") int patientId, @RequestBody Map<String, String> request) {
        try {
            User user = userService.findById(patientId);
            if (user == null) {
                // Nếu không tìm thấy bệnh nhân với ID đã cho, trả về 404 Not Found
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bệnh nhân với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if (user.getRole().getId() != 1) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            } else {
                user.setIsActive("0");
                String description = request.get("description");
                user.setDescription(description);
                userService.saveUser(user);

                // Trả về 200 OK và ApiResponse thông báo thành công
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Mở khóa bệnh nhân thành công.", user);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Nếu có ngoại lệ xảy ra trong quá trình xử lý, trả về 500 Internal Server Error
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.3.5. Khóatài khoản của bác sĩ
    @PutMapping("doctor/{doctorId}/lock")
    public ResponseEntity<ApiResponse> lockDoctor(@PathVariable("doctorId") int patientId) {
        try {
            User user = userService.findById(patientId);
            if (user == null) {
                // Nếu không tìm thấy bác ĩ với ID đã cho, trả về 404 Not Found
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bác sĩ với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if ( user.getRole().getId() != 2) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            // Lock bác sĩ bằng cách đặt mô tả (description) thành null và isActive thành "1"
            user.setDescription(null);
            user.setIsActive("1");
            userService.saveUser(user);

            // Trả về 200 OK và ApiResponse thông báo thành công
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Khóa bác sĩ thành công.", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Nếu có ngoại lệ xảy ra trong quá trình xử lý, trả về 500 Internal Server Error
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.3.5. hủy khóa tài khoản của bác sĩ
    @PutMapping("doctor/{doctorId}/unlock")
    public ResponseEntity<ApiResponse> unlockDoctor(@PathVariable("doctorId") int patientId, @RequestBody Map<String, String> request) {
        try {
            User user = userService.findById(patientId);
            if (user == null) {
                // Nếu không tìm thấy bác sĩ với ID đã cho, trả về 404 Not Found
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bác sĩ với ID đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if (user.getRole().getId() != 2) {
                ApiResponse response = new ApiResponse(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền thực hiện tác vụ này.", null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            } else {
                user.setIsActive("0");
                String description = request.get("description");
                user.setDescription(description);
                userService.saveUser(user);

                // Trả về 200 OK và ApiResponse thông báo thành công
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Mở khóa bác sĩ thành công.", user);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Nếu có ngoại lệ xảy ra trong quá trình xử lý, trả về 500 Internal Server Error
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.3.4. Thêm tài khoản của bác sĩ
    @PostMapping("/saveDoctor")
    public ResponseEntity<ApiResponse> saveDoctor(@RequestBody DoctorUser doctorUser) {
        try {
            // Tạo một đối tượng User mới
            User doctor = new User();
            doctor.setName(doctorUser.getDoctor().getName());
            doctor.setEmail(doctorUser.getDoctor().getEmail());
            doctor.setPassword(doctorUser.getDoctor().getPassword());
            doctor.setAddress(doctorUser.getDoctor().getAddress());
            doctor.setPhone(doctorUser.getDoctor().getPhone());
            doctor.setGender(doctorUser.getDoctor().getGender());
            // Lưu thông tin bác sĩ vào bảng Users
            userService.saveDoctor(doctor);

            // Gán id mới của bác sĩ sau khi lưu vào Users
            doctorUser.getDoctor().setId(doctor.getId());

            // Tìm specialization và lưu thông tin bác sĩ vào bảng DoctorUser
            Specialization specialization = specializationService.findSpecializationByName(doctorUser.getSpecialization().getName());
            doctorUser.setSpecialization(specialization);

            // Tìm clinic và lưu thông tin bác sĩ vào bảng DoctorUser
            Clinic clinic = clinicService.findUserByName(doctorUser.getClinic().getName());
            doctorUser.setClinic(clinic);

            // Tìm place và lưu thông tin bác sĩ vào bảng DoctorUser
            Place place = placeService.findUserByName(doctorUser.getPlace().getName());
            doctorUser.setPlace(place);

            doctorUserService.saveDoctor(doctorUser);

            // Trả về ApiResponse với mã trạng thái 200 OK và thông báo thành công
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Lưu thông tin bác sĩ thành công.", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về ApiResponse với mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}

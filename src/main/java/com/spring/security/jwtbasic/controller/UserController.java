package com.spring.security.jwtbasic.controller;

import com.spring.security.jwtbasic.entity.*;
import com.spring.security.jwtbasic.util.SearcBySpecializationDTO;
import com.spring.security.jwtbasic.util.UserDTO;
import com.spring.security.jwtbasic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorUserService doctorUserService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ScheduleService scheduleService;

    //5.1.2. Đăng ký
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO userDTO) {
        try {
            // Kiểm tra xem mật khẩu và nhập lại mật khẩu có khớp hay không
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Mật khẩu không khớp.", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Tạo đối tượng User mới
            User newUser = new User();
            newUser.setName(userDTO.getName());
            newUser.setGender(userDTO.getGender());
            newUser.setEmail(userDTO.getEmail());
            newUser.setPhone(userDTO.getPhone());
            newUser.setAddress(userDTO.getAddress());
            newUser.setPassword(userDTO.getPassword());

            // Lưu thông tin người dùng vào CSDL
            userService.saveUser(newUser);

            // Trả về ApiResponse với mã trạng thái 200 OK và thông báo thành công
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Đăng ký thành công.", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về ApiResponse với mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.3 Quên mật khẩu
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody UserDTO userDTO) {
        try {
            User theUser = userService.findByEmail(userDTO.getEmail());
            System.out.println(theUser);
            if (theUser != null) {
                // Kiểm tra xem mật khẩu và nhập lại mật khẩu có khớp hay không
                if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                    ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Mật khẩu không khớp.", null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
                theUser.setPassword(userDTO.getPassword());

                // Lưu thông tin người dùng vào CSDL
                userService.saveUser(theUser);
                // Nếu tìm thấy thông tin người dùng, trả về mã trạng thái 200 OK và thông báo thành công
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Lấy mật khẩu thành công.", theUser);
                return ResponseEntity.ok(response);
            } else {
                // Nếu không tìm thấy thông tin người dùng, trả về mã trạng thái 404 Not Found và thông báo "Không tìm thấy thông tin người dùng."
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Người dùng với email đã đăng ký không tồn tại.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.4. Hiển thị thông tin của các chuyên khoa nổi bật
    @GetMapping("/specializations")
    public ResponseEntity<ApiResponse> getSpecializations() {
        try {
            List<Specialization> specializations = specializationService.getSpecializations();
            if (specializations.isEmpty()) {
                // Nếu không có dữ liệu, trả về mã trạng thái 204 No Content và thông báo "Không có dữ liệu."
                ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT.value(), "Không có dữ liệu.", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            } else {
                // Nếu có dữ liệu, trả về danh sách chuyên khoa và mã trạng thái 200 OK
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Thành công.", specializations);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.5. Hiển thị thông tin của các cơ sở y tế nổi bật
    @GetMapping("/clinics")
    public ResponseEntity<ApiResponse> getClinics() {
        try {
            List<Clinic> clinics = clinicService.getClinics();
            if (clinics.isEmpty()) {
                // Nếu không có dữ liệu, trả về mã trạng thái 204 No Content và thông báo "Không có dữ liệu."
                ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT.value(), "Không có dữ liệu.", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            } else {
                // Nếu có dữ liệu, trả về danh sách clinics và mã trạng thái 200 OK
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Thành công.", clinics);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.6. Hiển thị thông tin cá nhân:
    @GetMapping("/information")
    public ResponseEntity<ApiResponse> getInformation(Authentication authentication) {
        try {
            String email = authentication.getName();
            System.out.println("email: " + email);
            User theUser = userService.findByEmail(email);
            System.out.println(theUser);
            if (theUser != null) {
                // Nếu tìm thấy thông tin người dùng, trả về mã trạng thái 200 OK và thông báo thành công
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Lấy thông tin người dùng thành công.", theUser);
                return ResponseEntity.ok(response);
            } else {
                // Nếu không tìm thấy thông tin người dùng, trả về mã trạng thái 404 Not Found và thông báo "Không tìm thấy thông tin người dùng."
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy thông tin người dùng.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.8. Tìm kiếm theo chuyên khoa của bác sĩ
    @GetMapping("/search-name/{name}")
    public ResponseEntity<ApiResponse> searchDoctorUser(@PathVariable("name") String name) {
        try {
            List<SearcBySpecializationDTO> doctorUsers = doctorUserService.findByDoctorUserName(name);
            if (doctorUsers.isEmpty()) {
                // Nếu không tìm thấy dữ liệu, trả về mã trạng thái 204 No Content và thông báo "Không tìm thấy dữ liệu."
                ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT.value(), "Không tìm thấy dữ liệu.", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            } else {
                // Nếu tìm thấy dữ liệu, trả về danh sách DoctorUser và mã trạng thái 200 OK
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Tìm thấy dữ liệu.", doctorUsers);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.7. Tìm kiếm chung
    @GetMapping("/search-all/{name}")
    public ResponseEntity<ApiResponse> searchAll(@PathVariable("name") String name) {
        try {
            List<DoctorUser> doctorUsers = doctorUserService.findAll(name);
            if (doctorUsers.isEmpty()) {
                // Nếu không tìm thấy dữ liệu, trả về mã trạng thái 204 No Content và thông báo "Không tìm thấy dữ liệu."
                ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT.value(), "Không tìm thấy dữ liệu.", null);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            } else {
                // Nếu tìm thấy dữ liệu, trả về danh sách DoctorUser và mã trạng thái 200 OK
                ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Tìm thấy dữ liệu.", doctorUsers);
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            // Xảy ra ngoại lệ, trả về mã trạng thái 500 Internal Server Error và thông báo lỗi
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //5.1.9. Đặt lịch khám
    @PostMapping("/schedules")
    public ResponseEntity<ApiResponse> addSchedule(@RequestBody Schedule schedule) {
        try {
            String nameDoctor = schedule.getDoctor().getName();
            User theDoctor = userService.findUserByName(nameDoctor);
            if (theDoctor == null) {
                // If the user with the given name is not found, return a 404 Not Found status code.
                ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bác sĩ với tên đã cho.", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                schedule.setDoctor(theDoctor);
                scheduleService.saveSchedule(schedule);
                // If the schedule is successfully saved, return a 201 Created status code.
                ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Thêm lịch trình thành công.", schedule);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
        } catch (Exception e) {
            // If there is an exception, return a 500 Internal Server Error status code.
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Đã xảy ra lỗi trong quá trình xử lý yêu cầu.", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}

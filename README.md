# Mô tả dự an: 
-Dự án đặt lịch khám bệnh cung cấp các giải pháp khám chữa bệnh cho người bệnh, đi kèm với những tiện ích để thăm khám thông qua việc:
tìm kiếm, đặt lịch, tra cứu hồ sơ, ... Từ đó tạo ra sự tin tưởng cũng như an tâm về đội ngũ y bác sĩ cũng như hệ thống tương ứng.
Mọi thông tin bệnh lý của bệnh nhân đều được bảo mật, sẽ chỉ có bác sĩ thăm khám theo dõi và cập nhật thông tin kịp thời.
-thao tác đối với REST API để tạo ra một Web Service.
# Thiết kế API: 
Về mặt API trả về dữ liệu tương ứng thì chỉ cần thao tác được cơ sở dữ liệu để có thể hiển thị ra những thông báo trả về (message). Nếu có lỗi trả về hãy lập tức trả về kèm với mã lỗi.
# Tổ chức dự án:
Về mặt xây dựng và tổ chức dự án, học viên sẽ phải tự thiết kế theo mô hình khuôn mẫu của MVC, tức là sẽ có các lớp package như: model, view, controller, config, ... 
# Các chức năng: 
1. Dành cho người dùng hệ thống (user)

1.1. Đăng nhập

Như đã nói, trong phần này chúng ta làm việc với JWT, nên các request sẽ dưới dạng REST API tương ứng.

Thông tin đăng nhập sẽ bao gồm:

Email
Mật khẩu
Bạn sẽ phải tạo ra API để cho phép request mà cần phải có token để xác thực thì mới cho phép sử dụng những chức năng cơ bản của hệ thống.

Chức năng đăng nhập sẽ xác thực việc bạn vào hệ thống với quyền tương ứng. Từ đó có thể hiển thị chức năng hệ thống phù hợp.

Lưu ý: Không cần phải phát triển cơ sở dữ liệu giống 100% ở dưới đây, chỉ cần phân tích được những thực thể, thể hiện rõ mối quan hệ của từng đối tượng và đáp ứng đủ chức năng của bài toán đưa ra.



1.2. Đăng ký

Chức năng đăng ký sẽ bao gồm những thông tin cá nhân cơ bản như sau:

Họ và tên
Giới tính
Email
Số điện thoại
Địa chỉ
Mật khẩu
Nhập lại mật khẩu
Hãy trả về tin nhắn (message) tương ứng khi đăng ký thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.3 Quên mật khẩu

Việc quên mật khẩu sẽ được xác thực thông gửi email tới email mà người dùng đã đăng ký với hệ thống tương ứng.

Lưu ý: Ở đây tồn tại 2 request tương ứng là: 

1 : Request gửi đi để xác nhận email.
2: Request gửi để xác thực lại mật khẩu tương ứng bao gồm (mật khẩu và nhập lại mật khẩu).
Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.4. Hiển thị thông tin của các chuyên khoa nổi bật

Ở trang chủ sẽ hiển thị luôn những chuyên khoa nổi bật của phòng khám tương ứng: cơ xương khớp, thần kinh, tiêu hóa, tim mạch, ...

Ở đây chỉ đơn giản là cần có 1 API để trả về toàn bộ những thông tin của chuyên khoa nổi bật (chuyên khoa được tìm kiếm hoặc đặt lịch khám nhiều nhất).

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.5. Hiển thị thông tin của các cơ sở y tế nổi bật

Ở trang chủ sẽ hiển thị luôn những cơ sở y tế khám chữa nổi bật của các phòng khám tương ứng: Bệnh viện Việt Đức, Bệnh Viện Chợ Rẫy, ...

Ở đây chỉ đơn giản là cần có 1 API để trả về toàn bộ những thông tin trên tương ứng. (Hãy mô tả chi tiết hơn những thông tin trên: Địa chỉ, thời gian làm việc, Lưu ý quan trọng, Chi phí khám, ...)

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.6. Hiển thị thông tin cá nhân:

Sau khi đăng nhập thành công xong, chúng ta có thể truy cập từ trang chủ để hiển thị ra những thông tin cá nhân của bản thân (Bao gồm những thông tin đã đăng ký với hệ thống và lịch sự khám chữa bệnh).

Ở đây chỉ đơn giản là cần có 1 API để trả về toàn bộ những thông tin trên tương ứng (Học viên có thể bổ sung thêm 1 API để cập nhật những thông tin cá nhân).

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.7. Tìm kiếm chung

Ở trang chủ sẽ hiển thị rất nhiều thông tin, bạn có thể lọc bớt những thông tin đó thông qua việc tìm kiếm tổng quát, bao gồm theo:

Khu vực
Danh mục (theo tình hình bệnh lý)
Mức giá
Cơ sở y tế
Ở đây chỉ đơn giản là cần có 1 API để trả về toàn bộ những thông tin tìm kiếm trên tương ứng NHƯNG theo sau là những ‘keyword’ trên để giúp bạn lọc theo thông tin một cách chính xác.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.8. Tìm kiếm theo chuyên khoa của bác sĩ

Ở trang chủ sẽ hiển thị rất nhiều thông tin, bạn có thể lọc bớt những thông tin đó thông qua việc tìm kiếm chung, nhưng bạn hoàn toàn có thể tìm theo chuyên khoa của bác sĩ mà mình cần.

Ví dụ: Bác sĩ nội khoa, bác sĩ tai mũi họng, ...

Ở đây chỉ đơn giản là cần có 1 API để trả về toàn bộ những thông tin tìm kiếm trên tương ứng NHƯNG theo sau là ‘keyword’ trên để giúp bạn lọc theo thông tin một cách chính xác.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

1.9. Đặt lịch khám

Khi bạn muốn khám bệnh, bạn cần phải đặt lịch khám chữa tương ứng, đi kèm với những thông tin chuẩn bị khám chữa bệnh như sau:

Tên bác sĩ
Khung giờ khám
Giá khám
Thông tin cá nhân như: tên, giới tính, số điện thoại, ngày tháng năm sinh, địa chỉ, lý do thăm khám.
Ở đây chỉ đơn giản là cần có 1 API để bạn có thể xác thực toàn bộ thông tin trên.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

2. Dành cho bác sĩ

2.1. Đăng nhập

Như đã nói, do phần này chúng ta làm việc với JWT, nên các request sẽ dưới dạng REST API tương ứng.

Bạn sẽ phải tạo ra API để cho phép request mà cần phải có token để xác thực thì mới cho phép sử dụng những chức năng cơ bản của hệ thống.

Chức năng đăng nhập sẽ xác thực việc bạn vào hệ thống với quyền tương ứng. Từ đó có thể hiển thị chức năng hệ thống phù hợp.

2.2. Hiển thị danh sách bệnh nhân

Bác sĩ có thể xem được danh sách thăm khám của các bệnh nhân, sẽ bao gồm những thông tin cơ bản đã khám như sau:

Họ và tên
Giới tính
Địa chỉ
Bệnh lý bao gồm: Bệnh lý cơ bản và mô tả chi tiết bệnh lý
Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

2.3. Nhận/hủy lịch khám của bệnh nhân

Bác sĩ có thể nhận hoặc hủy lịch tương ứng đối với bệnh nhân muốn tới thăm khám. Đối với hủy lịch thì bổ sung thêm trường thông tin ‘mô tả’ để nêu rõ lý do hủy lịch khám tương ứng.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

3. Dành cho người quản lý hệ thống (Admin)

3.1. Đăng nhập

Như đã nói, do phần này chúng ta làm việc với JWT, nên các request sẽ dưới dạng REST API tương ứng.

Bạn sẽ phải tạo ra API để cho phép request mà cần phải có token để xác thực thì mới cho phép sử dụng những chức năng cơ bản của hệ thống.

Chức năng đăng nhập sẽ xác thực việc bạn vào hệ thống với quyền tương ứng. Từ đó có thể hiển thị chức năng hệ thống phù hợp.

3.2. Khóa/hủy khóa tài khoản của bệnh nhân

Admin có thể khóa hoặc hủy khóa tương ứng đối với tài khoản bệnh nhân. Đối với khóa tài khoản thì bổ sung thêm trường thông tin ‘mô tả’ để nêu rõ lý do khóa tài khoản.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

3.4. Thêm tài khoản của bác sĩ

Admin có thể thêm được tài khoản bác sĩ vào hệ thống với các trường thông tin cơ bản sẽ tương tự đối với người dùng hệ thống nhưng bổ sung thêm:

Giới thiệu chung
Quá trình đào tạo
Các thành tựu đạt được
Các chuyên khoa phụ trách
Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.

3.5. Khóa/hủy khóa tài khoản của bác sĩ

Admin có thể khóa hoặc hủy khóa tương ứng đối với tài khoản bác sĩ. Đối với khóa tài khoản thì bổ sung thêm trường thông tin ‘mô tả’ để nêu rõ lý do khóa tài khoản.

Hãy trả về tin nhắn (message) tương ứng khi gửi request thông tin thành công, trả về mã lỗi khi có ngoại lệ xảy ra.


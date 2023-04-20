# [**Table Of Content**](#table-of-content)
- [**Table Of Content**](#table-of-content)
- [**Introduction**](#introduction)
- [**Topic**](#topic)
- [**API Document**](#api-document)
- [**Database**](#database)
- [**Major Features**](#major-features)
  - [**1. Login**](#1-login)
  - [**2. Register**](#2-register)
  - [**3. OTP**](#3-otp)
- [**Post Script**](#post-script)
- [**Made with 💘 and Android**](#made-with--and-android)
# [**Introduction**](#introduction)
hào các bạn, mình tên là Nguyễn Thành Phong. 
Mã số N19DCCN188. 
Niên khóa 2019-2024. 

Lời đầu tiên mình xin chào các bạn và cảm ơn tất cả các bạn đang ở đây. Trong tài liệu này mình sẽ chia sẻ tất cả những gì các bạn cần biết khi làm đồ án 
và đề tài do mình thực hiện để các bạn có thể tham khảo. Mình hi vọng phần tài liệu mình viết tiếp theo đây 
sẽ hỗ trợ phần nào cho các bạn.

Đồ án này có tất cả là 2 thành phần bao gồm:
* [**API**](https://github.com/Phong-Kaster/PTIT-Do-An-Tot-Nghiep) 
* [**Ứng dụng Android**](#) (Hiện tại)
Các bạn đang đọc phần mô tả chi tiết về `ứng dụng Android` trong đoán này trong tài liệu này mình sẽ mô tả chi tiết về cấu trúc của các thư mục các tính năng nổi bật nhất và một số những cái lưu ý khi các bạn tham khảo đồ án này.

# [**Topic**](#topic)

<p align="center">
    <img src="./photo/topic.png" />
</p>


Có thể giải thích yêu cầu đề tài ngắn gọn như sau:

**Android** - Ứng dụng để bệnh nhân đặt lịch khám bệnh, theo dõi phác đồ điều trị và bệnh án của mình. Có thể đặt lịch khám bệnh
cho người thân trong gia đình như ông, bà, bố, mẹ & không nhất thiết người khám bệnh phải là bản thân mình.

# [**API Document**](#api-document)

Mình có soạn thảo và liệt kê chi tiết cách sử dụng các chức năng mà mình đã xây dựng thành tài liệu.
Nếu các bạn có nhu cầu muốn tham khảo, hãy ấn vào [**đây**](https://github.com/Phong-Kaster/PTIT-Do-An-Tot-Nghiep-API-Document) để đọc chi tiết cách sử dụng API này.

# [**Database**](#database)

<p align="center">
    <img src="./photo/database-version-12-prototype.png" />
</p>
<h3 align="center">

***Sơ đồ cơ sở dữ liệu***
  
# [**Major Features**](#major-features)

Phần này mình sẽ giới thiệu về tất cả các giao diện và các chức năng chính trong ứng dụng

## [**1. Login**](#1-login)

<p align="center">
    <img src="./photo/Android003.jpg" height="600px"/>
</p>
<h3 align="center">
***Màn hình đăng nhập***
</h3>
  
 Ứng dụng hỗ trợ người dùng hai tùy chọn để đăng nhập vào chương trình, bao gồm:

1. Đăng nhập bằng email và password đã đăng ký 

2. Đăng nhập bằng tài khoản email và otp
  
 Lưu ý:
  Tài khoản chưa xác thực thì không thể đăng nhập được

 ## [**2. Register**](#2-register)
  
<p align="center">
    <img src="./photo/Android004.jpg" height="600px"/>
    <img src="./photo/Android004.jpg" height="600px"/>
    <img src="./photo/Android004.jpg" height="600px"/>
</p>
<h3 align="center">
***Màn hình đăng ký***
</h3>

Ở đây người dùng có thể tạo tài khoản. Tài khoản người dùng sẽ được tao khi và chỉ khi tất cả được tích xanh ✅ ở đây tài khoản người dùng sẽ được lưu trử ở database như action = 'false' vì chưa xác thực tải khoản này sẽ tựng động bị xóa nếu trong 5 phút người dùng không xác thực
<p align="center">
      <img  src="./photo/select-users.jpg" height="600px"/>
</p>
<h3 align="center">
***Dữ liệu các tài khoản trong Database***
</h3>
  
## [**3. OTP**](#3-otp)
  
  
<p align="center">
    <img src="./photo/otp.jpg" height="600px"/>
</p>
  
  Ở đây người dùng sẽ được 2 sự lựa chọn:
  - gửi lại otp
  - xác thực otp
  khi xác thự thành công thì tài khoản ở chế độ action = 'true' 

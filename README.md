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
hào các bạn, mình tên là Huỳnh Ngô Anh Thái. 
Mã số N19DCCN188. 
Niên khóa 2019-2024. 

Lời đầu tiên mình xin chào các bạn và cảm ơn tất cả các bạn đang ở đây. Trong tài liệu này mình sẽ chia sẻ tất cả những gì các bạn cần biết khi làm đồ án 
và đề tài do mình thực hiện để các bạn có thể tham khảo. Mình hi vọng phần tài liệu mình viết tiếp theo đây 
sẽ hỗ trợ phần nào cho các bạn.

Đồ án này có tất cả là 2 thành phần bao gồm:
* [**API**](https://github.com/huynhngoanhthai/API-Android) 
* [**Ứng dụng Android**](#) (Hiện tại)
Các bạn đang đọc phần mô tả chi tiết về `ứng dụng Android` trong đoán này trong tài liệu này mình sẽ mô tả chi tiết về cấu trúc của các thư mục các tính năng nổi bật nhất và một số những cái lưu ý khi các bạn tham khảo đồ án này.

# [**Topic**](#topic)

<p align="center">
    <img src="./photo/topic.png" />
</p>


Có thể giải thích yêu cầu đề tài ngắn gọn như sau:

**Android** - Ứng dụng để bệnh nhân đặt nước, mình đang làm về chức năng đăng nhập đăng ký, có otp.

# [**API Document**](#api-document)

Mình có soạn thảo và liệt kê chi tiết cách sử dụng các chức năng mà mình đã xây dựng thành tài liệu.
Nếu các bạn có nhu cầu muốn tham khảo, hãy ấn vào [**đây**](https://github.com/huynhngoanhthai/API-Android) để đọc chi tiết cách sử dụng API này.

# [**Database**](#database)

<p align="center">
    <img src="./photo/table-users.png" />
</p>
<h3 align="center">

***Sơ đồ cơ sở dữ liệu***
  
# [**Major Features**](#major-features)

Phần này mình sẽ giới thiệu về tất cả các giao diện và các chức năng chính trong ứng dụng

## [**1. Login**](#1-login)

<p align="center">
    <img src="./photo/login.png" height="600px"/>
    <img src="./photo/login-compele.png" height="600px"/>
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
    <img src="./photo/register.png" height="600px"/>
    <img src="./photo/register-security-suggest.png" height="600px"/>
    <img src="./photo/register-compele.png" height="600px"/>
    
  
</p>
<h3 align="center">
***Màn hình đăng ký***
</h3>

Ở đây người dùng có thể tạo tài khoản. Tài khoản người dùng sẽ được tao khi và chỉ khi tất cả được tích xanh ✅ ở đây tài khoản người dùng sẽ được lưu trử ở database như action = 'false' vì chưa xác thực tải khoản này sẽ tựng động bị xóa nếu trong 5 phút người dùng không xác thực
<p align="center">
      <img  src="./photo/table-user-not-action.png" height="150px"/>
</p>
<h3 align="center">
***Dữ liệu các tài khoản trong Database***
</h3>
  
## [**3. OTP**](#3-otp)
  
  
<p align="center">
    <img src="./photo/otp.png" height="600px"/>
</p>
  Ở đây người dùng sẽ được 2 sự lựa chọn:
  - gửi lại otp
  - xác thực otp
  khi xác thự thành công thì tài khoản ở chế độ action = 'true' 
  <p align="center">
    <img  src="./photo/table-user-action.png" height="150px"/>
</p>
<h3 align="center">
***Dữ liệu các tài khoản trong Database***
</h3>


# [**Post Script**](#post-script)

Phía trên là toàn bộ các chức năng và giao diện mà mình đã thực hiện trong trong đồ án tốt nghiệp này. 


Phong hi vọng phần chia sẻ ở phía trên sẽ ít nhiều đem lại hữu ích cho các bạn.


# [**Made with 💘 and Android**](#made-with--and-android)
  

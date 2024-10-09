package com.pvh.gym_management.services.Impl;

import com.pvh.gym_management.dtos.*;
import com.pvh.gym_management.mappers.PTInfoDTOMapper;
import com.pvh.gym_management.mappers.UserDTOMapper;
import com.pvh.gym_management.mappers.UserPublicDTOMapper;
import com.pvh.gym_management.mappers.UserUpdateDTOMapper;
import com.pvh.gym_management.pojo.PTDetail;
import com.pvh.gym_management.pojo.Role;
import com.pvh.gym_management.pojo.User;
import com.pvh.gym_management.repositories.PTDetailRepository;
import com.pvh.gym_management.repositories.RoleRepository;
import com.pvh.gym_management.repositories.UserRepository;
import com.pvh.gym_management.repositories.WorkScheduleRepository;
import com.pvh.gym_management.services.EmailService;
import com.pvh.gym_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPublicDTOMapper userPublicDTOMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserUpdateDTOMapper userUpdateDTOMapper;

    @Autowired
    private UserDTOMapper userDTOMapper;



    @Autowired
    private PTDetailRepository PTDetailRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Role role = roleRepository.findById(1L).get();
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public Optional<UserPublicDTO> getUserById(int id) {
        return userRepository.findById(id)
                .map(userPublicDTOMapper::toUserPublicDTO);
    }


    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public List<UserPublicDTO> findInactiveUsers() {
        return userRepository.findByIsActiveFalse()
                .stream()
                .map(userPublicDTOMapper::toUserPublicDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserPublicDTO toggleUserActiveStatus(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        boolean newVerifiedStatus = !user.isActive(); // Trạng thái mới của `active`
        user.setActive(newVerifiedStatus);
        userRepository.save(user);

        String htmlContent = newVerifiedStatus
                ? String.format(
                "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; color: #333;\">"
                        + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px;\">"
                        + "<h2 style=\"color: #28a745; text-align: center;\">Tài khoản của bạn đã được xác nhận</h2>"
                        + "<p>Xin chào %s,</p>"
                        + "<p>Tài khoản của bạn đã được kích hoạt thành công và bạn có thể đăng nhập vào hệ thống.</p>"
                        + "<p style=\"text-align: right;\">Trân trọng,<br>Đội ngũ hỗ trợ Bus Station</p>"
                        + "</div>"
                        + "<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777;\">"
                        + "<p>Email này được gửi tự động, vui lòng không trả lời.</p>"
                        + "</div></body></html>",
                user.getFirstName() + " " + user.getLastName()
        )
                : String.format(
                "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; color: #333;\">"
                        + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px;\">"
                        + "<h2 style=\"color: #dc3545; text-align: center;\">Tài khoản của bạn đã bị khóa</h2>"
                        + "<p>Xin chào %s,</p>"
                        + "<p>Tài khoản của bạn đã bị khóa. Nếu bạn có thắc mắc, vui lòng liên hệ đội ngũ hỗ trợ để biết thêm chi tiết.</p>"
                        + "<p style=\"text-align: right;\">Trân trọng,<br>Đội ngũ hỗ trợ Bus Station</p>"
                        + "</div>"
                        + "<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777;\">"
                        + "<p>Email này được gửi tự động, vui lòng không trả lời.</p>"
                        + "</div></body></html>",
                user.getFirstName() + " " + user.getLastName()
        );
        String subject = newVerifiedStatus ? "Tài khoản của bạn đã được xác nhận" : "Tài khoản của bạn đã bị khóa";
        emailService.sendHtmlEmail(user.getEmail(), subject, htmlContent);

        return userPublicDTOMapper.toUserPublicDTO(user);
    }

    @Override
    public UserPublicDTO updateUser(int userId, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userUpdateDTOMapper.updateUserFromDto(userUpdateDTO, user);

        user = userRepository.save(user);

        return userPublicDTOMapper.toUserPublicDTO(user);
    }

    @Override
    public UserDTO updateUserRoleToPT(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role ptRole = roleRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("PT role not found"));
        user.setRole(ptRole);
        User updatedUser = userRepository.save(user);

        String htmlContent = String.format(
                "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; color: #333;\">"
                        + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px;\">"
                        + "<h2 style=\"color: #28a745; text-align: center;\">Chúc mừng! Bạn đã trở thành PT</h2>"
                        + "<p>Xin chào %s,</p>"
                        + "<p>Chúng tôi rất vui mừng thông báo rằng tài khoản của bạn đã được nâng cấp lên vai trò Personal Trainer (PT). Bạn đã trở thành một phần quan trọng trong cộng đồng huấn luyện viên của chúng tôi.</p>"
                        + "<p>Bây giờ, bạn có thể truy cập các tính năng và tài nguyên dành riêng cho PT trong hệ thống của chúng tôi. Hãy bắt đầu hành trình mới và tạo ra sự khác biệt cho các thành viên của bạn!</p>"
                        + "<p style=\"text-align: right;\">Trân trọng,<br>Đội ngũ hỗ trợ Gym Management</p>"
                        + "</div>"
                        + "<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777;\">"
                        + "<p>Email này được gửi tự động, vui lòng không trả lời.</p>"
                        + "</div></body></html>",
                user.getFirstName() + " " + user.getLastName()
        );

        String subject = "Chúc mừng bạn đã trở thành PT!";
        emailService.sendHtmlEmail(user.getEmail(), subject, htmlContent);

        return userDTOMapper.toUserDTO(updatedUser);
    }

    @Override
    public void resetPassword(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newPassword = username;
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        String htmlContent = String.format(
                "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; color: #333;\">"
                        + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px;\">"
                        + "<h2 style=\"color: #28a745; text-align: center;\">Đặt lại mật khẩu thành công</h2>"
                        + "<p>Xin chào %s,</p>"
                        + "<p>Mật khẩu mới của bạn đã được đặt lại thành: <strong>%s</strong>.</p>"
                        + "<p>Vui lòng đăng nhập và thay đổi mật khẩu của bạn ngay lập tức.</p>"
                        + "<p style=\"text-align: right;\">Trân trọng,<br>Đội ngũ hỗ trợ Gym Management</p>"
                        + "</div>"
                        + "<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777;\">"
                        + "<p>Email này được gửi tự động, vui lòng không trả lời.</p>"
                        + "</div></body></html>",
                user.getFirstName() + " " + user.getLastName(),
                newPassword
        );

        String subject = "Đặt lại mật khẩu thành công";
        emailService.sendHtmlEmail(user.getEmail(), subject, htmlContent);
    }

    @Override
    public void changePassword(int userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        String htmlContent = String.format(
                "<html><body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; color: #333;\">"
                        + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); padding: 20px;\">"
                        + "<h2 style=\"color: #28a745; text-align: center;\">Mật khẩu đã được thay đổi thành công</h2>"
                        + "<p>Xin chào %s,</p>"
                        + "<p>Mật khẩu của bạn đã được thay đổi thành công. Vui lòng sử dụng mật khẩu mới để đăng nhập.</p>"
                        + "<p style=\"text-align: right;\">Trân trọng,<br>Đội ngũ hỗ trợ Gym Management</p>"
                        + "</div>"
                        + "<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777;\">"
                        + "<p>Email này được gửi tự động, vui lòng không trả lời.</p>"
                        + "</div></body></html>",
                user.getFirstName() + " " + user.getLastName()
        );

        String subject = "Mật khẩu của bạn đã được thay đổi thành công";
        emailService.sendHtmlEmail(user.getEmail(), subject, htmlContent);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userDTOMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PTInfoDTO> getAllPTDetails() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.getRole().getId() == 3)
                .map(user -> {
                    PTDetail ptDetail = PTDetailRepository.findByUserId(user.getId()).orElse(null);

                    return PTInfoDTO.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .address(user.getAddress())
                            .avatar(user.getAvatar())
                            .isActive(user.isActive())
                            .salary(ptDetail != null ? ptDetail.getSalary() : null)
                            .build();
                })
                .collect(Collectors.toList());
    }
}

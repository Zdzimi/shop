package pl.zdzimi.shop.service;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.EmployeeDTO;
import pl.zdzimi.shop.model.Role;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class EmployeesService {

  private final UsersRepository usersRepository;
  private final MailsService mailsService;
  private final PasswordEncoder passwordEncoder;
  private final CodesService codesService;

  public Collection<EmployeeDTO> findAll() {
    return usersRepository.findAllEmployees(Role.ROLE_ADMIN.name()).stream()
        .map(Mapper::mapToDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  public void create(EmployeeDTO employeeDTO) {
    User employee = Mapper.mapToUser(employeeDTO);
    employee.setPassword(passwordEncoder.encode(employee.getSurname()));
    User savedEmployee = usersRepository.save(employee);
    savedEmployee.setCode(codesService.prepareCode(savedEmployee.getId()));
    usersRepository.save(savedEmployee);
    mailsService.sendCode(savedEmployee.getEmail(), savedEmployee.getCode());
  }

  public void delete(Long id) {
    usersRepository.findById(id).ifPresent(usersRepository::delete);
  }

  private static class Mapper {

    static EmployeeDTO mapToDTO(User user) {
      EmployeeDTO employeeDTO = new EmployeeDTO();
      employeeDTO.setId(user.getId());
      employeeDTO.setName(user.getName());
      employeeDTO.setSurname(user.getSurname());
      employeeDTO.setEmail(user.getEmail());
      employeeDTO.setRole(user.getRole());
      return employeeDTO;
    }

    public static User mapToUser(EmployeeDTO employeeDTO) {
      User user = new User();
      user.setName(employeeDTO.getName());
      user.setSurname(employeeDTO.getSurname());
      user.setEmail(employeeDTO.getEmail());
      user.setRole(Role.ROLE_ADMIN.name());
      return user;
    }
  }

}

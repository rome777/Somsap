package somsap.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import somsap.webapi.Repository.MemberTypeRepository;
import somsap.webapi.model.MemberType;

//import javax.transaction.Transaction;

@SpringBootApplication
public class WebApiApplication implements CommandLineRunner{

	@Autowired
	MemberTypeRepository memberTypeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		save();
	}
	
	@Transactional
	void save() throws Exception {
		memberTypeRepository.save(new MemberType("admin", "관리자~"));
		memberTypeRepository.save(new MemberType("developer", "개발자"));
		throw new Exception();
		//TODO JPA에서 Transaction 거는 법 알아내기
		/*try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}*/
	}
}

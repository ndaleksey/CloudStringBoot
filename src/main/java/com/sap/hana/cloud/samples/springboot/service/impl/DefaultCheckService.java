package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
@Service
public class DefaultCheckService implements CheckService {

	@Override
	public void save(Check client) {

	}

	@Override
	public Check findById(Long id) {
		return null;
	}

	@Override
	public List<Check> findAll() {
		return null;
	}

	@Override
	public List<Check> findByPage(int page, int size) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public void deleteByIds(Long[] ids) {

	}
}
/*public class DefaultCheckService implements CheckService {
	private ProductRepository productRepository;
	private CheckRepository repository;

	public DefaultCheckService(ProductRepository productRepository, CheckRepository repository) {
		this.productRepository = productRepository;
		this.repository = repository;
	}

	@PostConstruct
	public void init() {
		ProductCategory meat = new ProductCategory("Мясо");
		ProductCategory fish = new ProductCategory("Рыба");
		ProductCategory fruit = new ProductCategory("Фрукты");
		ProductCategory vegetables = new ProductCategory("Овощи");

		Product chicken = new Product("Курица", meat);
		Product pork = new Product("Свинина", meat);
		Product beaf = new Product("Говядина", meat);
		Product mutton = new Product("Баранина", meat);

		Product salmon = new Product("Лосось", fish);
		Product trout = new Product("Форель", fish);
		Product perch = new Product("Окунь", fish);

		Product apple = new Product("Яблоко", fruit);
		Product banana = new Product("Банан", fruit);
		Product peach = new Product("Персик", fruit);
		Product grapes = new Product("Виноград", fruit);
		Product lemon = new Product("Лимон", fruit);
		Product pear = new Product("Груша", fruit);

		Product potato = new Product("Картофель", vegetables);
		Product tomato = new Product("Томат", vegetables);
		Product cucumber = new Product("Огурец", vegetables);
		Product onion = new Product("Лук", vegetables);
		Product beet = new Product("Свекла", vegetables);
		Product carrot = new Product("Морковь", vegetables);
		Product cabbage = new Product("Купуста", vegetables);

		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Check check1 = new Check("1", LocalDateTime.of(yesterday, LocalTime.of(10, 20, 0)), "Магазин 1", CheckStatus.PROCESSED);
		check1.getPositions().add(new CheckPosition("1", salmon, 1, 600));
		check1.getPositions().add(new CheckPosition("2", beaf, 1, 400));
		check1.getPositions().add(new CheckPosition("3", potato, 3, 20));

		Check check2 = new Check("2", LocalDateTime.of(today, LocalTime.of(17, 35, 0)), "Магазин 1", CheckStatus.CREATED);
		check2.getPositions().add(new CheckPosition("1", peach, 1, 200));
		check2.getPositions().add(new CheckPosition("2", apple, 1, 50));
		check2.getPositions().add(new CheckPosition("3", banana, 0.5, 80));
		check2.getPositions().add(new CheckPosition("4", tomato, 0.5, 50));
		check2.getPositions().add(new CheckPosition("5", cucumber, 0.5, 70));

		Check check3 = new Check("1", LocalDateTime.of(today, LocalTime.of(9, 40, 0)), "Магазин 2", CheckStatus.CREATED);
		check3.getPositions().add(new CheckPosition("1", pork, 2, 400));
		check3.getPositions().add(new CheckPosition("2", beaf, 4, 450));
		check3.getPositions().add(new CheckPosition("3", onion, 2, 30));
		check3.getPositions().add(new CheckPosition("4", tomato, 2, 60));

		Check check4 = new Check("2", LocalDateTime.of(today, LocalTime.of(18, 10, 0)), "Магазин 2", CheckStatus.PROCESSED);
		check4.getPositions().add(new CheckPosition("1", lemon, 1, 90));
		check4.getPositions().add(new CheckPosition("2", grapes, 2, 180));
		check4.getPositions().add(new CheckPosition("2", carrot, 1, 40));

//		save(check1);
//		save(check2);
//		save(check3);
//		save(check4);
//		productRepository.save(tomato);
	}

	@Override
	public void save(Check client) {
		repository.save(client);
	}

	@Override
	public Check findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Check> findAll() {
		List<Check> checks = new ArrayList<>();
		StreamSupport.stream(repository.findAll().spliterator(), false).forEach(c -> checks.add(c));
		return checks;
	}

	@Override
	public List<Check> findByPage(int page, int size) {
		List<Check> checks = new ArrayList<>();
		StreamSupport.stream(repository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(c -> checks.add(c));
		return checks;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (Long id : ids) {
			repository.deleteById(id);
		}
	}
}*/

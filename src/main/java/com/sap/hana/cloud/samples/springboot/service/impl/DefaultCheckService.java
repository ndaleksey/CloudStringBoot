package com.sap.hana.cloud.samples.springboot.service.impl;

import com.sap.hana.cloud.samples.springboot.dao.CheckPositionRepository;
import com.sap.hana.cloud.samples.springboot.dao.CheckRepository;
import com.sap.hana.cloud.samples.springboot.dao.ProductCategoryRepository;
import com.sap.hana.cloud.samples.springboot.dao.ProductRepository;
import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.model.check.CheckStatus;
import com.sap.hana.cloud.samples.springboot.service.CheckService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by Shishkov A.V. on 29.06.18.
 */
//@Transactional
@Service
public class DefaultCheckService implements CheckService {
	private ProductRepository productRepository;
	private ProductCategoryRepository categoryRepository;
	private CheckRepository checkRepository;
	private CheckPositionRepository checkPositionRepository;

	public DefaultCheckService(ProductRepository productRepository, ProductCategoryRepository categoryRepository,
							   CheckRepository checkRepository, CheckPositionRepository checkPositionRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.checkRepository = checkRepository;
		this.checkPositionRepository = checkPositionRepository;
	}

	@PostConstruct
	public void init() {
		ProductCategory meat = categoryRepository.save(new ProductCategory("Мясо"));
		ProductCategory fish = categoryRepository.save(new ProductCategory("Рыба"));
		ProductCategory fruit = categoryRepository.save(new ProductCategory("Фрукты"));
		ProductCategory vegetables = categoryRepository.save(new ProductCategory("Овощи"));

		Product chicken = productRepository.save(new Product("Курица", meat));
		Product pork = productRepository.save(new Product("Свинина", meat));
		Product beaf = productRepository.save(new Product("Говядина", meat));
		Product mutton = productRepository.save(new Product("Баранина", meat));

		Product salmon = productRepository.save(new Product("Лосось", fish));
		Product trout = productRepository.save(new Product("Форель", fish));
		Product perch = productRepository.save(new Product("Окунь", fish));

		Product apple = productRepository.save(new Product("Яблоко", fruit));
		Product banana = productRepository.save(new Product("Банан", fruit));
		Product peach = productRepository.save(new Product("Персик", fruit));
		Product grapes = productRepository.save(new Product("Виноград", fruit));
		Product lemon = productRepository.save(new Product("Лимон", fruit));
		Product pear = productRepository.save(new Product("Груша", fruit));

		Product potato = productRepository.save(new Product("Картофель", vegetables));
		Product tomato = productRepository.save(new Product("Томат", vegetables));
		Product cucumber = productRepository.save(new Product("Огурец", vegetables));
		Product onion = productRepository.save(new Product("Лук", vegetables));
		Product beet = productRepository.save(new Product("Свекла", vegetables));
		Product carrot = productRepository.save(new Product("Морковь", vegetables));
		Product cabbage = productRepository.save(new Product("Купуста", vegetables));

		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Check check1 = new Check("1", LocalDateTime.of(yesterday, LocalTime.of(10, 20, 0)), "Магазин 1", CheckStatus.PROCESSED);
		check1.getPositions().add(new CheckPosition(check1, "1", salmon, 1, 600));
		check1.getPositions().add(new CheckPosition(check1, "2", beaf, 1, 400));
		check1.getPositions().add(new CheckPosition(check1, "3", onion, 3, 20));

		Check check2 = new Check("2", LocalDateTime.of(today, LocalTime.of(17, 35, 0)), "Магазин 1", CheckStatus
				.CREATED);
		check2.getPositions().add(new CheckPosition(check2, "1", mutton, 1, 550));
		check2.getPositions().add(new CheckPosition(check2, "2", potato, 1, 30));
		check2.getPositions().add(new CheckPosition(check2, "3", beaf, 0.5, 80));
		check2.getPositions().add(new CheckPosition(check2, "4", grapes, 0.5, 80));
		check2.getPositions().add(new CheckPosition(check2, "3", tomato, 0.6, 60));
		check2.getPositions().add(new CheckPosition(check2, "5", cucumber, 0.5, 70));

		Check check3 = new Check("1", LocalDateTime.of(today, LocalTime.of(9, 40, 0)), "Магазин 2", CheckStatus
				.CREATED);
		check3.getPositions().add(new CheckPosition(check3, "1", pork, 2, 400));
		check3.getPositions().add(new CheckPosition(check3, "2", beaf, 4, 450));
		check3.getPositions().add(new CheckPosition(check3, "3", onion, 2, 30));
		check3.getPositions().add(new CheckPosition(check3, "4", tomato, 2, 60));

		Check check4 = new Check("2", LocalDateTime.of(today, LocalTime.of(18, 10, 0)), "Магазин 2", CheckStatus.PROCESSED);
		check4.getPositions().add(new CheckPosition(check4, "1", lemon, 1, 90));
		check4.getPositions().add(new CheckPosition(check4, "2", grapes, 2, 180));
		check4.getPositions().add(new CheckPosition(check4, "3", carrot, 1, 40));

		save(check1);
		save(check2);
		save(check3);
		save(check4);
	}

	@Transactional
	@Override
	public Check save(Check check) {
		return checkRepository.save(check);
	}

	@Override
	public Check findById(Long id) {
		return checkRepository.findById(id).get();
	}

	@Override
	public List<Check> findAll() {
		List<Check> checks = new ArrayList<>();
		StreamSupport.stream(checkRepository.findAll().spliterator(), false).forEach(c -> checks.add(c));
		return checks;
	}

	@Override
	public List<Check> findByPage(int page, int size) {
		List<Check> checks = new ArrayList<>();
		StreamSupport.stream(checkRepository.findAll(PageRequest.of(page, size)).spliterator(), false).forEach(c -> checks.add(c));
		return checks;
	}

	@Override
	public void delete(Long id) {
		checkRepository.deleteById(id);
	}

	@Override
	public void deleteByIds(Long[] ids) {
		for (Long id : ids) {
			checkRepository.deleteById(id);
		}
	}
}
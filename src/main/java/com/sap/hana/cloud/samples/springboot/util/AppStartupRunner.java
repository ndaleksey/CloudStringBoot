package com.sap.hana.cloud.samples.springboot.util;

import com.sap.hana.cloud.samples.springboot.model.Client;
import com.sap.hana.cloud.samples.springboot.model.Gender;
import com.sap.hana.cloud.samples.springboot.model.Product;
import com.sap.hana.cloud.samples.springboot.model.ProductCategory;
import com.sap.hana.cloud.samples.springboot.model.check.Check;
import com.sap.hana.cloud.samples.springboot.model.check.CheckPosition;
import com.sap.hana.cloud.samples.springboot.model.check.CheckStatus;
import com.sap.hana.cloud.samples.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Shishkov A.V. on 06.07.18.
 */
@Component
public class AppStartupRunner implements ApplicationRunner {
	@Autowired
	private CheckService checkService;

	@Autowired
	private CheckPositionService checkPositionService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductCategoryService productCategoryService;


	@Autowired
	private ProductService productService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		initDataBase();
	}

	private void initDataBase() {
		initClients();
		initChecks();
	}

	private void initChecks() {
		ProductCategory meat = productCategoryService.save(new ProductCategory("Мясо"));
		ProductCategory fish = productCategoryService.save(new ProductCategory("Рыба"));
		ProductCategory fruit = productCategoryService.save(new ProductCategory("Фрукты"));
		ProductCategory vegetables = productCategoryService.save(new ProductCategory("Овощи"));

		Product chicken = productService.save(new Product("Курица", meat));
		Product pork = productService.save(new Product("Свинина", meat));
		Product beaf = productService.save(new Product("Говядина", meat));
		Product mutton = productService.save(new Product("Баранина", meat));

		Product salmon = productService.save(new Product("Лосось", fish));
		Product trout = productService.save(new Product("Форель", fish));
		Product perch = productService.save(new Product("Окунь", fish));

		Product apple = productService.save(new Product("Яблоко", fruit));
		Product banana = productService.save(new Product("Банан", fruit));
		Product peach = productService.save(new Product("Персик", fruit));
		Product grapes = productService.save(new Product("Виноград", fruit));
		Product lemon = productService.save(new Product("Лимон", fruit));
		Product pear = productService.save(new Product("Груша", fruit));

		Product potato = productService.save(new Product("Картофель", vegetables));
		Product tomato = productService.save(new Product("Томат", vegetables));
		Product cucumber = productService.save(new Product("Огурец", vegetables));
		Product onion = productService.save(new Product("Лук", vegetables));
		Product beet = productService.save(new Product("Свекла", vegetables));
		Product carrot = productService.save(new Product("Морковь", vegetables));
		Product cabbage = productService.save(new Product("Купуста", vegetables));

		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Check check1 = checkService.save(new Check("1", LocalDateTime.of(yesterday, LocalTime.of(10, 20, 0)),
				"Магазин 1", CheckStatus.PROCESSED));
		checkPositionService.save(new CheckPosition(check1, "1", salmon, 1, 600));
		checkPositionService.save(new CheckPosition(check1, "2", beaf, 1, 400));
		checkPositionService.save(new CheckPosition(check1, "3", onion, 3, 20));

		Check check2 = checkService.save(new Check("2", LocalDateTime.of(today, LocalTime.of(17, 35, 0)), "Магазин 1", CheckStatus.CREATED));
		checkPositionService.save(new CheckPosition(check2, "1", mutton, 1, 550));
		checkPositionService.save(new CheckPosition(check2, "2", potato, 1, 30));
		checkPositionService.save(new CheckPosition(check2, "3", beaf, 0.5, 80));
		checkPositionService.save(new CheckPosition(check2, "4", grapes, 0.5, 80));
		checkPositionService.save(new CheckPosition(check2, "3", tomato, 0.6, 60));
		checkPositionService.save(new CheckPosition(check2, "5", cucumber, 0.5, 70));

		Check check3 = checkService.save(new Check("1", LocalDateTime.of(today, LocalTime.of(9, 40, 0)), "Магазин 2", CheckStatus.CREATED));
		checkPositionService.save(new CheckPosition(check3, "1", pork, 2, 400));
		checkPositionService.save(new CheckPosition(check3, "2", beaf, 4, 450));
		checkPositionService.save(new CheckPosition(check3, "3", onion, 2, 30));
		checkPositionService.save(new CheckPosition(check3, "4", tomato, 2, 60));

		Check check4 = checkService.save(new Check("2", LocalDateTime.of(today, LocalTime.of(18, 10, 0)), "Магазин " +
				"2", CheckStatus.PROCESSED));
		checkPositionService.save(new CheckPosition(check4, "1", lemon, 1, 90));
		checkPositionService.save(new CheckPosition(check4, "2", grapes, 2, 180));
		checkPositionService.save(new CheckPosition(check4, "3", carrot, 1, 40));
	}

	private void initClients() {
		Client client1 = new Client(
				"Владимир",
				"Семенов",
				"Иванович",
				Gender.MALE,
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-908-555-55-55",
				"Москва");

		Client client2 = new Client(
				"Петр",
				"Волков",
				"Сергеевич",
				Gender.MALE,
				LocalDate.of(1989, 4, 22),
				"volkov@mail.ru",
				"+7-928-155-45-52",
				"Саратов");

		Client client3 = new Client(
				"Екатерина",
				"Лукина",
				"Эдуардовна",
				Gender.FEMALE,
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-952-235-87-56",
				"Самара");

		clientService.save(client1);
		clientService.save(client2);
		clientService.save(client3);
	}
}

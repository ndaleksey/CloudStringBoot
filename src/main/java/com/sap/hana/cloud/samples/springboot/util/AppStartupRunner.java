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
import java.util.Arrays;

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
		initChecksAndClients();
	}

	private void initChecksAndClients() {
		ProductCategory meat = new ProductCategory("Мясо");
		ProductCategory fish = new ProductCategory("Рыба");
		ProductCategory fruit = new ProductCategory("Фрукты");
		ProductCategory vegetables = new ProductCategory("Овощи");

		Product chicken = productService.save(new Product("Курица", meat));
		Product pork = productService.save(new Product("Свинина", meat));
		Product beef = productService.save(new Product("Говядина", meat));
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

		meat.getProducts().addAll(Arrays.asList(chicken, pork, beef, mutton));
		fish.getProducts().addAll(Arrays.asList(salmon, perch, trout));
		fruit.getProducts().addAll(Arrays.asList(apple, banana, peach, grapes, lemon, pear));
		vegetables.getProducts().addAll(Arrays.asList(potato, tomato, cucumber, onion, beet, carrot, cabbage));

		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		Check check1 = new Check("1", LocalDateTime.of(yesterday, LocalTime.of(10, 20, 0)),
				"Магазин 1", CheckStatus.PROCESSED);
		CheckPosition position11 = new CheckPosition(check1, "1", salmon, 1, 600);
		CheckPosition position12 = new CheckPosition(check1, "2", beef, 1, 400);
		CheckPosition position13 = new CheckPosition(check1, "3", onion, 3, 20);
		check1.getPositions().addAll(Arrays.asList(position11, position12, position13));

		Check check2 = new Check("2", LocalDateTime.of(today, LocalTime.of(17, 35, 0)), "Магазин 2", CheckStatus
				.CREATED);
		CheckPosition position21 = new CheckPosition(check2, "1", mutton, 1, 550);
		CheckPosition position22 = new CheckPosition(check2, "2", potato, 1, 30);
		CheckPosition position23 = new CheckPosition(check2, "3", beef, 0.5, 80);
		CheckPosition position24 = new CheckPosition(check2, "4", grapes, 0.5, 80);
		CheckPosition position25 = new CheckPosition(check2, "3", tomato, 0.6, 60);
		CheckPosition position26 = new CheckPosition(check2, "5", cucumber, 0.5, 70);
		check2.getPositions().addAll(Arrays.asList(position21, position22, position23, position24, position25,
				position26));

		Check check3 = new Check("3", LocalDateTime.of(today, LocalTime.of(9, 40, 0)), "Магазин 3", CheckStatus
				.CREATED);
		CheckPosition position31 = new CheckPosition(check3, "1", pork, 2, 400);
		CheckPosition position32 = new CheckPosition(check3, "2", beef, 4, 450);
		CheckPosition position33 = new CheckPosition(check3, "3", onion, 2, 30);
		CheckPosition position34 = new CheckPosition(check3, "4", tomato, 2, 60);
		check3.getPositions().addAll(Arrays.asList(position31, position32, position33, position34));

		Check check4 = new Check("4", LocalDateTime.of(today, LocalTime.of(18, 10, 0)), "Магазин " +
				"4", CheckStatus.PROCESSED);
		CheckPosition position41 = new CheckPosition(check4, "1", lemon, 1, 90);
		CheckPosition position42 = new CheckPosition(check4, "2", grapes, 2, 180);
		CheckPosition position43 = new CheckPosition(check4, "3", carrot, 1, 40);
		check4.getPositions().addAll(Arrays.asList(position41, position42, position43));

		Client client1 = new Client(
				"Владимир",
				"Семенов",
				"Иванович",
				Gender.MALE,
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-908-555-55-55",
				"Москва");

		client1.getChecks().addAll(Arrays.asList(check1));
		clientService.save(client1);

		Client client2 = new Client(
				"Петр",
				"Волков",
				"Сергеевич",
				Gender.MALE,
				LocalDate.of(1989, 4, 22),
				"volkov@mail.ru",
				"+7-928-155-45-52",
				"Саратов");
		client2.getChecks().addAll(Arrays.asList(check2, check3));
		clientService.save(client2);

		Client client3 = new Client(
				"Екатерина",
				"Лукина",
				"Эдуардовна",
				Gender.FEMALE,
				LocalDate.of(1978, 1, 2),
				"semenov@mail.ru",
				"+7-952-235-87-56",
				"Самара");

		clientService.save(client3);

		checkService.save(check4);
	}
}

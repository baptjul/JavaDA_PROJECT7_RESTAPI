package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

/**
 * Tests for the {@link RuleNameService} class
 *
 * To run these tests, you need to use a Spring test runner
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;
	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * Deletes all ruleName before each test
	 */
	@BeforeEach
	void setup() {
		ruleNameRepository.deleteAll();
		ruleNameRepository.flush();
	}

	/**
	 * Test saveRuleName method
	 */
	@Test
	public void saveRuleNameTest() {
		RuleName ruleName = new RuleName();
		ruleName.setName("Rule Name");
		ruleName.setDescription("Description");
		ruleName.setJson("Json");
		ruleName.setTemplate("Template");
		ruleName.setSqlStr("SQL");
		ruleName.setSqlPart("SQL Part");

		RuleName newRuleName = ruleNameService.saveRuleName(ruleName);

		assertNotNull(newRuleName.getId());
		assertEquals("Rule Name", newRuleName.getName());
	}

	/**
	 * Test updateRuleName method
	 */
	@Test
	public void updateRuleNameTest() throws IllegalAccessException {
		RuleName ruleName = new RuleName();
		ruleName.setName("Rule Name");
		ruleName.setDescription("Description");
		ruleName.setJson("Json");
		ruleName.setTemplate("Template");
		ruleName.setSqlStr("SQL");
		ruleName.setSqlPart("SQL Part");

		RuleName newRuleName = ruleNameService.saveRuleName(ruleName);

		RuleName ruleNameUpdate = new RuleName();
		ruleNameUpdate.setName("Rule Name Update");
		ruleNameUpdate.setDescription("Description");
		ruleNameUpdate.setJson("Json");
		ruleNameUpdate.setTemplate("Template");
		ruleNameUpdate.setSqlStr("SQL");
		ruleNameUpdate.setSqlPart("SQL Part");

		RuleName updatedRuleName = ruleNameService.updateRuleName(newRuleName.getId(), ruleNameUpdate);

		assertNotNull(updatedRuleName.getId());
		assertEquals("Rule Name Update", updatedRuleName.getName());
	}

	/**
	 * Test getAllRuleNames method
	 */
	@Test
	public void getAllRuleNamesTest() {
		RuleName firstRuleName = new RuleName();
		firstRuleName.setName("Rule Name 1");
		firstRuleName.setDescription("Description");
		firstRuleName.setJson("Json");
		firstRuleName.setTemplate("Template");
		firstRuleName.setSqlStr("SQL");
		firstRuleName.setSqlPart("SQL Part");
		ruleNameService.saveRuleName(firstRuleName);

		RuleName secondRuleName = new RuleName();
		secondRuleName.setName("Rule Name 2");
		secondRuleName.setDescription("Description");
		secondRuleName.setJson("Json");
		secondRuleName.setTemplate("Template");
		secondRuleName.setSqlStr("SQL");
		secondRuleName.setSqlPart("SQL Part");
		ruleNameService.saveRuleName(secondRuleName);

		assertTrue(ruleNameService.getAllRuleNames().size() > 0);
	}

	/**
	 * Test deleteRuleName method
	 */
	@Test
	public void deleteRuleNameTest() {
		RuleName firstRuleName = new RuleName();
		firstRuleName.setName("Rule Name 1");
		firstRuleName.setDescription("Description");
		firstRuleName.setJson("Json");
		firstRuleName.setTemplate("Template");
		firstRuleName.setSqlStr("SQL");
		firstRuleName.setSqlPart("SQL Part");
		RuleName firstNewRuleName = ruleNameService.saveRuleName(firstRuleName);

		RuleName secondRuleName = new RuleName();
		secondRuleName.setName("Rule Name 2");
		secondRuleName.setDescription("Description");
		secondRuleName.setJson("Json");
		secondRuleName.setTemplate("Template");
		secondRuleName.setSqlStr("SQL");
		secondRuleName.setSqlPart("SQL Part");
		ruleNameService.saveRuleName(secondRuleName);

		ruleNameService.deleteRuleName(firstNewRuleName.getId());

		assertNull(ruleNameService.getRuleNameById(firstNewRuleName.getId()));
	}
}

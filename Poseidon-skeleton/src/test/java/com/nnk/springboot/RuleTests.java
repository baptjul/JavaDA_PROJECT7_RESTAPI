package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@BeforeEach
	void setup() {
		ruleNameRepository.deleteAll();
		ruleNameRepository.flush();
	}

//	@Test
//	public void ruleTest() {
//		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
//
//		// Save
//		rule = ruleNameRepository.save(rule);
//		Assert.assertNotNull(rule.getId());
//		Assert.assertTrue(rule.getName().equals("Rule Name"));
//
//		// Update
//		rule.setName("Rule Name Update");
//		rule = ruleNameRepository.save(rule);
//		Assert.assertTrue(rule.getName().equals("Rule Name Update"));
//
//		// Find
//		List<RuleName> listResult = ruleNameRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = rule.getId();
//		ruleNameRepository.delete(rule);
//		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
//		Assert.assertFalse(ruleList.isPresent());
//	}

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
	//
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

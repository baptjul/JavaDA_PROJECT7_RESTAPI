package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurveService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurveService curveService;
	@Autowired
	private CurvePointRepository curvePointRepository;

	@BeforeEach
	void setup() {
		curvePointRepository.deleteAll();
		curvePointRepository.flush();
	}

//	@Test
//	public void curvePointTest() {
//		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
//
//		// Save
//		curvePoint = curvePointRepository.save(curvePoint);
//		Assert.assertNotNull(curvePoint.getId());
//		Assert.assertTrue(curvePoint.getCurveId() == 10);
//
//		// Update
//		curvePoint.setCurveId(20);
//		curvePoint = curvePointRepository.save(curvePoint);
//		Assert.assertTrue(curvePoint.getCurveId() == 20);
//
//		// Find
//		List<CurvePoint> listResult = curvePointRepository.findAll();
//		Assert.assertTrue(listResult.size() > 0);
//
//		// Delete
//		Integer id = curvePoint.getId();
//		curvePointRepository.delete(curvePoint);
//		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
//		Assert.assertFalse(curvePointList.isPresent());
//	}

	@Test
	public void saveCurvePointTest() {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);

		CurvePoint newCurvePoint = curveService.saveCurvePoint(curvePoint);

		assertNotNull(newCurvePoint.getId());
		assertEquals(10, newCurvePoint.getCurveId(), 0.0001d);
	}

	@Test
	public void updateCurvePointTest() throws IllegalAccessException {
		CurvePoint curvePoint = new CurvePoint();
		curvePoint.setCurveId(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);

		CurvePoint newCurvePoint = curveService.saveCurvePoint(curvePoint);

		CurvePoint curvePointUpdate = new CurvePoint();
		curvePointUpdate.setCurveId(20);
		curvePointUpdate.setTerm(10d);
		curvePointUpdate.setValue(30d);

		CurvePoint updatedcurvePoint = curveService.updateCurvePoint(newCurvePoint.getId(), curvePointUpdate);

		assertNotNull(updatedcurvePoint.getId());
		assertEquals(20, updatedcurvePoint.getCurveId(), 0.0001d);
	}

	@Test
	public void findAllTest() {
		CurvePoint firstCurvePoint = new CurvePoint();
		firstCurvePoint.setCurveId(10);
		firstCurvePoint.setTerm(10d);
		firstCurvePoint.setValue(30d);
		curveService.saveCurvePoint(firstCurvePoint);

		CurvePoint secondCurvePoint = new CurvePoint();
		firstCurvePoint.setCurveId(20);
		firstCurvePoint.setTerm(10d);
		firstCurvePoint.setValue(40d);
		curveService.saveCurvePoint(secondCurvePoint);

		assertTrue(curveService.getAllCurve().size() > 0);
	}
//
	@Test
	public void deleteCurvePointTest() {
		CurvePoint firstCurvePoint = new CurvePoint();
		firstCurvePoint.setCurveId(10);
		firstCurvePoint.setTerm(10d);
		firstCurvePoint.setValue(30d);
		CurvePoint firstNewCurve = curveService.saveCurvePoint(firstCurvePoint);

		CurvePoint secondCurvePoint = new CurvePoint();
		firstCurvePoint.setCurveId(20);
		firstCurvePoint.setTerm(10d);
		firstCurvePoint.setValue(40d);
		curveService.saveCurvePoint(secondCurvePoint);

		curveService.deleteCurvePoint(firstNewCurve.getId());

		assertNull(curveService.getCurvePointById(firstNewCurve.getId()));
	}
}

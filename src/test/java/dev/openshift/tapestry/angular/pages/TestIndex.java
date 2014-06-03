/**
 * Simple JUnit test with the PageTester.
 */
package dev.openshift.tapestry.angular.pages;

import junit.framework.Assert;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.junit.Test;

import dev.openshift.tapestry.angular.pages.Index;

/**
 * @author ffacon
 * 
 */
public class TestIndex extends Assert {

	/**
	 * Test if the page contains id = color and if the text inside this id is
	 * the same as defined in constant.
	 */
	@Test
	public final void test() {
		String appPackage = "dev.openshift.tapestry.angular";
		String appName = "App";
		PageTester tester = new PageTester(appPackage, appName,
				"src/main/webapp");
		Document doc = tester.renderPage("Index");

	}
}
package protractordemotest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {

    private WebElement tableElement;

    public Table(WebElement table) {
        this.tableElement = table;
    }

    public List<WebElement> getRow(final int index) {
        return tableElement.findElements(By.xpath("(//tbody//tr)[" + index + "]/td"));
    }
}

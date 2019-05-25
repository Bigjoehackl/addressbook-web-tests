package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() {
        app.goTo().gotoHomePage();
        app.contact().initContactModification();
        app.contact().fillContactForm(
                new ContactData().withFirstname("test_name").withLastname("test_surname"), true);
        app.contact().submitContactModification();
        app.contact().returnToHomePage();
    }

    @Test
    public void testContactModificationDb() {
        app.goTo().gotoHomePage();
        app.contact().initContactModification();
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/stickeroid_5bf5459dcb8e2.png");
        ContactData newContact = new ContactData().withFirstname("test_name").withLastname("test_surname").withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.contact().fillContactFormsDb(newContact,true);
        app.contact().submitContactModification();
        app.contact().returnToHomePage();
    }
}

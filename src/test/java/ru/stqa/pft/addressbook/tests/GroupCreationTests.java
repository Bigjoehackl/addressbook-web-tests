package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("test1", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        /*
        //Способ сравнения с использованием цикла
        int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
         }*/


        /*
        //Способ сравнения с использованием компоратора
        Comparator<? super GroupData> byId = new Comparator<GroupData>() {
            @Override
            public int compare(GroupData o1, GroupData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        int max = after.stream().max(byId).get().getId();*/


        /*
        //Способ сравнения с помощью лямбда выражений
        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        group.setId(max);*/

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}

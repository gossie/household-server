package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ShoppingListServiceTest {

	private ShoppingListService shoppingListService;

	@Test
	public void testGetShoppingList() {
		ShoppingList expectedShoppingList = new ShoppingList("1L");

		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.determineShoppingList("1L")).thenReturn(expectedShoppingList);

		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList actualShoppingList = shoppingListService.getShoppingList("1L");

		assertThat(actualShoppingList).isSameAs(expectedShoppingList);
	}

    @Test
    public void testAddShoppingListGroup() {
        List<ShoppingListGroup> groups = List.of(new ShoppingListGroup("3L", "group", Collections.emptyList()));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);
        ShoppingList result = shoppingListService.addShoppingListGroup("1L", new ShoppingListGroup(null, "group2", Collections.emptyList()));

        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group.hasName("group").hasSize(0))
            .shoppingListGroup(1, group -> group.hasName("group2").hasSize(0));
    }

	@Test
	public void testAddShoppingListItems() {
	    List<ShoppingListGroup> groups = List.of(new ShoppingListGroup("3L", "group", Collections.emptyList()));
		ShoppingList shoppingList = new ShoppingList("1L", groups);

		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
		when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList result = shoppingListService.addShoppingListItems("1L", "3L", List.of(new ShoppingListItem("2L", "new", false, new byte[]{})));

		assertThat(result)
		    .hasSize(1)
		    .shoppingListGroup(0, group -> group.hasName("group").hasSize(1).shoppingListItem(0, el -> el.hasName("new").isDeselected()));
	}

    @Test
        public void testRemoveSelectedItemsFromShoppingListGroup() {
            List<ShoppingListItem> items1 = new ArrayList<>();
            items1.add(new ShoppingListItem("1L", "one", false, new byte[]{}));
            items1.add(new ShoppingListItem("2L", "two", true, new byte[]{}));

            List<ShoppingListItem> items2 = new ArrayList<>();
            items2.add(new ShoppingListItem("3L", "three", false, new byte[]{}));
            items2.add(new ShoppingListItem("4L", "four", true, new byte[]{}));

            ShoppingListGroup group1 = new ShoppingListGroup("8L", "group1", items1);
            ShoppingListGroup group2 = new ShoppingListGroup("9L", "group2", items2);

            ShoppingList shoppingList = new ShoppingList("5L", List.of(group1, group2));

            ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
            when(shoppingListRepository.determineShoppingList("5L")).thenReturn(shoppingList);
            when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

            shoppingListService = new ShoppingListService(shoppingListRepository);
            ShoppingList result = shoppingListService.removeSelectedItemsFromShoppingListGroup("5L", "8L");

            assertThat(result)
                .hasSize(2)
                .shoppingListGroup(0, group -> group
                        .hasName("group1")
                        .hasSize(1)
                        .shoppingListItem(0, item -> item.hasName("one").isDeselected()))
                .shoppingListGroup(1, group -> group
                        .hasName("group2")
                        .hasSize(2)
                        .shoppingListItem(0, item -> item.hasName("three").isDeselected())
                        .shoppingListItem(1, item -> item.hasName("four").isSelected()));
        }

    @Test
    public void testRemoveAllSelectedItems() {
        List<ShoppingListItem> items1 = new ArrayList<>();
        items1.add(new ShoppingListItem("1L", "one", false, new byte[]{}));
        items1.add(new ShoppingListItem("2L", "two", true, new byte[]{}));

        List<ShoppingListItem> items2 = new ArrayList<>();
        items2.add(new ShoppingListItem("3L", "three", false, new byte[]{}));
        items2.add(new ShoppingListItem("4L", "four", true, new byte[]{}));

        ShoppingListGroup group1 = new ShoppingListGroup("8L", "group1", items1);
        ShoppingListGroup group2 = new ShoppingListGroup("9L", "group2", items2);

        ShoppingList shoppingList = new ShoppingList("5L", List.of(group1, group2));

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("5L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);
        ShoppingList result = shoppingListService.removeAllSelectedItems("5L");

        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group
                    .hasName("group1")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("one").isDeselected()))
            .shoppingListGroup(1, group -> group
                    .hasName("group2")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("three").isDeselected()));
    }

	@Test
	public void testToggleItem() {
	    List<ShoppingListItem> items1 = new ArrayList<>();
        items1.add(new ShoppingListItem("1L", "one", false, new byte[]{}));
        items1.add(new ShoppingListItem("2L", "two", true, new byte[]{}));

        List<ShoppingListItem> items2 = new ArrayList<>();
        items2.add(new ShoppingListItem("3L", "three", false, new byte[]{}));
        items2.add(new ShoppingListItem("4L", "four", true, new byte[]{}));

        ShoppingListGroup group1 = new ShoppingListGroup("8L", "group1", items1);
        ShoppingListGroup group2 = new ShoppingListGroup("9L", "group2", items2);

        ShoppingList shoppingList = new ShoppingList("5L", List.of(group1, group2));

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("5L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);

        ShoppingList result = shoppingListService.toggleItem("5L", "9L", "3L");

        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group
                    .hasName("group1")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("one").isDeselected())
                    .shoppingListItem(1, item -> item.hasName("two").isSelected()))
            .shoppingListGroup(1, group -> group
                    .hasName("group2")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("three").isSelected())
                    .shoppingListItem(1, item -> item.hasName("four").isSelected()));

        result = shoppingListService.toggleItem("5L", "8L", "2L");

        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group
                    .hasName("group1")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("one").isDeselected())
                    .shoppingListItem(1, item -> item.hasName("two").isDeselected()))
            .shoppingListGroup(1, group -> group
                    .hasName("group2")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("three").isSelected())
                    .shoppingListItem(1, item -> item.hasName("four").isSelected()));
	}

	@Test
    public void testDeleteShoppingListGroup() {
        List<ShoppingListGroup> groups = List.of(
                new ShoppingListGroup("3L", "group1", Collections.emptyList()),
                new ShoppingListGroup("4L", "group2", Collections.emptyList()));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);
        ShoppingList result = shoppingListService.deleteShoppingListGroup("1L", "4L");

        assertThat(result)
            .hasSize(1)
            .shoppingListGroup(0, group -> group.hasName("group1").hasSize(0));
    }

	@Test
    public void testThatGlobalGroupCannotBeDeleted() {
        List<ShoppingListGroup> groups = List.of(
                new ShoppingListGroup("3L", "group1", Collections.emptyList()),
                new ShoppingListGroup("4L", "Global", Collections.emptyList()));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);

        assertThatExceptionOfType(ShoppingListGroupNotDeletableException.class)
            .isThrownBy(() -> shoppingListService.deleteShoppingListGroup("1L", "4L"));
    }

    @Test
    public void testdeleteShoppingList() {
        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);

        shoppingListService = new ShoppingListService(shoppingListRepository);
        shoppingListService.deleteShoppingList("3L");

        verify(shoppingListRepository).deleteShoppingList("3L");
    }

    @Test
    public void testToggleShoppingListGroup_selectItems() {
        List<ShoppingListItem> items = List.of(
            new ShoppingListItem("4L", "item1", false, new byte[]{}),
            new ShoppingListItem("5L", "item2", false, new byte[]{})
        );
        List<ShoppingListGroup> groups = List.of(new ShoppingListGroup("3L", "group1", items));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);

        ShoppingList result = shoppingListService.toggleShoppingListGroup("1L", "3L");

        assertThat(result)
            .shoppingListGroup(0, group -> group
                .shoppingListItem(0, ShoppingListItemAssert::isSelected)
                .shoppingListItem(1, ShoppingListItemAssert::isSelected));
    }

    @Test
    public void testToggleShoppingListGroup_selectItems_oneItemAlreadySelected() {
        List<ShoppingListItem> items = List.of(
            new ShoppingListItem("4L", "item1", false, new byte[]{}),
            new ShoppingListItem("5L", "item2", true, new byte[]{})
        );
        List<ShoppingListGroup> groups = List.of(new ShoppingListGroup("3L", "group1", items));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);

        ShoppingList result = shoppingListService.toggleShoppingListGroup("1L", "3L");

        assertThat(result)
            .shoppingListGroup(0, group -> group
                .shoppingListItem(0, ShoppingListItemAssert::isSelected)
                .shoppingListItem(1, ShoppingListItemAssert::isSelected));
    }

    @Test
    public void testToggleShoppingListGroup_deselectItems() {
        List<ShoppingListItem> items = List.of(
            new ShoppingListItem("4L", "item1", true, new byte[]{}),
            new ShoppingListItem("5L", "item2", true, new byte[]{})
        );
        List<ShoppingListGroup> groups = List.of(new ShoppingListGroup("3L", "group1", items));
        ShoppingList shoppingList = new ShoppingList("1L", groups);

        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("1L")).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);

        shoppingListService = new ShoppingListService(shoppingListRepository);

        ShoppingList result = shoppingListService.toggleShoppingListGroup("1L", "3L");

        assertThat(result)
            .shoppingListGroup(0, group -> group
                .shoppingListItem(0, ShoppingListItemAssert::isDeselected)
                .shoppingListItem(1, ShoppingListItemAssert::isDeselected));
    }

    @Test
    public void testEditShoppingListItem_onlyName() {
	    var shoppingListRepository = mock(ShoppingListRepository.class);
	    when(shoppingListRepository.determineShoppingList("17L"))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffel", false, new byte[]{}))))));
	    when(shoppingListRepository.saveShoppingList(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, new byte[]{})))))))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, new byte[]{}))))));

	    shoppingListService = new ShoppingListService(shoppingListRepository);
	    var shoppingListItem = new ShoppingListItem("27L", "Kartoffeln", false, new byte[]{});
	    var shoppingList = shoppingListService.editItem("17L", "25L", "27L", shoppingListItem);

	    assertThat(shoppingList)
            .hasId("17L")
            .hasSize(1)
            .shoppingListGroup(0, group -> group
                .hasId("25L")
                .hasSize(1)
                .shoppingListItem(0, item -> item
                    .hasId("27L")
                    .hasName("Kartoffeln")
                    .isDeselected()
                )
            );
    }

    @Test
    public void testEditShoppingListItem_onlyState() {
        var shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("17L"))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, new byte[]{}))))));
        when(shoppingListRepository.saveShoppingList(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{})))))))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{}))))));

        shoppingListService = new ShoppingListService(shoppingListRepository);
        var shoppingListItem = new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{});
        var shoppingList = shoppingListService.editItem("17L", "25L", "27L", shoppingListItem);

        assertThat(shoppingList)
            .hasId("17L")
            .hasSize(1)
            .shoppingListGroup(0, group -> group
                .hasId("25L")
                .hasSize(1)
                .shoppingListItem(0, item -> item
                    .hasId("27L")
                    .hasName("Kartoffeln")
                    .isSelected()
                )
            );
    }

    @Test
    public void testEditShoppingListItem_nameAndState() {
        var shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("17L"))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffel", false, new byte[]{}))))));
        when(shoppingListRepository.saveShoppingList(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{})))))))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{}))))));

        shoppingListService = new ShoppingListService(shoppingListRepository);
        var shoppingListItem = new ShoppingListItem("27L", "Kartoffeln", true, new byte[]{});
        var shoppingList = shoppingListService.editItem("17L", "25L", "27L", shoppingListItem);

        assertThat(shoppingList)
            .hasId("17L")
            .hasSize(1)
            .shoppingListGroup(0, group -> group
                .hasId("25L")
                .hasSize(1)
                .shoppingListItem(0, item -> item
                    .hasId("27L")
                    .hasName("Kartoffeln")
                    .isSelected()
                )
            );
    }

    @Test
    public void testEditShoppingListItem_onlyImage() {
        var shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList("17L"))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, null))))));
        when(shoppingListRepository.saveShoppingList(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, "IMAGE".getBytes())))))))
            .thenReturn(new ShoppingList("17L", List.of(new ShoppingListGroup("25L", "Global", List.of(new ShoppingListItem("27L", "Kartoffeln", false, "IMAGE".getBytes()))))));

        shoppingListService = new ShoppingListService(shoppingListRepository);
        var shoppingListItem = new ShoppingListItem("27L", "Kartoffeln", false, "IMAGE".getBytes());
        var shoppingList = shoppingListService.editItem("17L", "25L", "27L", shoppingListItem);

        assertThat(shoppingList)
            .hasId("17L")
            .hasSize(1)
            .shoppingListGroup(0, group -> group
                .hasId("25L")
                .hasSize(1)
                .shoppingListItem(0, item -> item
                    .hasId("27L")
                    .hasName("Kartoffeln")
                    .hasImage("IMAGE".getBytes())
                    .isDeselected()
                )
            );
    }
}

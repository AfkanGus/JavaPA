package vkaretko;

import org.junit.Before;
import org.junit.Test;
import vkaretko.interfaces.MenuItems;
import vkaretko.menuitems.File;
import vkaretko.menuitems.FileOpen;
import vkaretko.menuitems.New;
import vkaretko.menuitems.Help;
import vkaretko.menuitems.HelpAbout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test-class for ConsoleMenu.
 *
 * @author Karetko Victor
 * @version 1.00
 * @since 07.12.2016
 */
public class ConsoleMenuTest {
    /**
     * MultiOS line separator.
     */
    private final String sep = System.getProperty("line.separator");
    /**
     * Console menu for tests.
     */
    private ConsoleMenu menu;

    /**
     * Init menu before tests.
     */
    @Before
    public void initConsoleMenuBeforeTests() {
        this.menu = new ConsoleMenu();
    }

    /**
     * Method checks printMenu method with two items.
     */
    @Test
    public void whenPrintMenuWithTwoLinesThenResultMenuWithTwoLinesInConsole() {
        File file = new File();
        Help help = new Help();
        ArrayList<MenuItems> testList = new ArrayList<>();
        testList.add(file);
        testList.add(help);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            this.menu.printMenu(testList);
            assertThat(out.toString(), is(String.format("File%sHelp%s", sep, sep)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Method checks printMenu method with two items.
     */
    @Test
    public void whenAddNewFileToFileThenGetListFromFileReturnFile() {
        File file = new File();
        New newFile = new New();
        file.addMenuItem(newFile);
        assertThat(newFile, is(file.getMenuItems().get(0)));
    }

    /**
     * Method checks printing list of menu items, when call init method of ConsoleMenu.
     */
    @Test
    public void whenInitConsoleMenuThenResultMenuWithDashes() {
        StringBuilder menuInLine = new StringBuilder();
        menuInLine.append(String.format("File%s--New%s----Project...   [opr]%s", sep, sep, sep));
        menuInLine.append(String.format("%-17s%s%s", "--Open", "[open]", sep));
        menuInLine.append(String.format("Help%s--About          [ab]%s", sep, sep));
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            this.menu.init();
            assertThat(out.toString(), is(menuInLine.toString()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Method test creating menu.
     */
    @Test
    public void whenCreateMenuWithFourItemsThenResultMenuWithFourItems() {
        ConsoleMenu conMenu = new ConsoleMenu();
        File file = new File();
        Help help = new Help();
        conMenu.addMenuItem(file);
        conMenu.addMenuItem(help);
        help.addMenuItem(new HelpAbout());
        file.addMenuItem(new FileOpen());
        String expectedMenu =
                String.format("File%s--Open           [open]%sHelp%s--About          [ab]%s", sep, sep, sep, sep);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            conMenu.printMenu(conMenu.getMenuItems());
            assertThat(out.toString(), is(expectedMenu));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

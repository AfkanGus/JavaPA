package vkaretko;

import org.junit.Test;
import vkaretko.models.*;
import vkaretko.start.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test-class for Tracker class
 *
 * @author Karetko Victor
 * @version 1.00
 * @since 04.11.2016
 */
public class TrackerTest {
    private int firstElementOfArray = 0;

    @Test
    public void whenAddedNewItemThenResultInTrackerZeroPositionIsSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Task("test1","testDescription",123L);
        tracker.add(item);
        assertThat(tracker.getAll()[firstElementOfArray], is(item));
    }

    @Test
    public void whenAddTaskAndEditItWithAnotherTaskThenResultAnotherTask() {
        Tracker tracker = new Tracker();
        Item itemTask = new Task("test1","testDescription",123L);
        Item itemTaskEdited = new Task("test2","testDescription2",1234L);
        tracker.add(itemTask);
        itemTaskEdited.setId(itemTask.getId());
        tracker.edit(itemTaskEdited);
        assertThat(tracker.getAll()[firstElementOfArray], is(itemTaskEdited));
    }

    @Test
    public void whenAddTwoTasksAndDeleteFirstTaskByIdThenResultArrayWithSecondTask() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Task("test1","testDescription",123L);
        Item itemSecond = new Task("test2","testDescription2",1234L);
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        tracker.delete(tracker.getAll()[firstElementOfArray].getId());
        assertThat(tracker.getAll()[firstElementOfArray], is(itemSecond));
    }

    @Test
    public void whenAddTwoTasksAndFindByNameThenResultFoundedTask() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Task("test1","testDescription",123L);
        Item itemSecond = new Task("test2","testDescription",123L);
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        assertThat(tracker.findByName(itemSecond.getName()), is(itemSecond));
    }

    @Test
    public void whenAddTwoTasksAndFindByIdThenResultFoundedTask() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Task("test1","testDescription",123L);
        Item itemSecond = new Task("test2","testDescription",123L);
        tracker.add(itemFirst);
        tracker.add(itemSecond);
        assertThat(tracker.findById(itemSecond.getId()), is(itemSecond));
    }

    @Test
    public void whenAddCommentToTaskThenResultInCommentsArrayAtZeroPositionIsSameComment() {
        Tracker tracker = new Tracker();
        Item item = new Task("test1","testDescription",123L);
        Comment comment = new Comment("Test comment");
        tracker.add(item);
        tracker.addComment(tracker.getAll()[firstElementOfArray].getId(), comment);
        assertThat(tracker.getAll()[firstElementOfArray].getComments()[firstElementOfArray], is(comment));
    }

}

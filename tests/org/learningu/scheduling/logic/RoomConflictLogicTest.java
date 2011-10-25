package org.learningu.scheduling.logic;

import org.learningu.scheduling.Schedule;
import org.learningu.scheduling.StartAssignment;
import org.learningu.scheduling.graph.ClassPeriod;
import org.learningu.scheduling.graph.Course;
import org.learningu.scheduling.graph.Room;
import org.learningu.scheduling.util.ModifiedState;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class RoomConflictLogicTest extends BaseLogicTest {

  @Override
  protected Iterable<Module> modules() {
    return Iterables.concat(super.modules(), ImmutableList.of(new AbstractModule() {
      @Override
      protected void configure() {
        bind(ScheduleLogic.class).to(RoomConflictLogic.class);
      }
    }));
  }

  public void testOverlappingRoomConflict() {
    Schedule.Factory factory = injector.getInstance(Schedule.Factory.class);
    Schedule schedule = factory.create();
    Course origami = getCourse("OrigamiCourse");
    Course math = getCourse("MathCourse");
    ClassPeriod tenAM = getPeriod("10AM");
    ClassPeriod elevenAM = getPeriod("11AM");
    Room harper142 = getRoom("Harper142");
    // Origami is two hours.
    ModifiedState<ScheduleValidator, Schedule> assign1 = schedule.assignStart(StartAssignment.create(
        tenAM,
        harper142,
        origami.getSection(0)));
    assertTrue(assign1.getResult().toString(), assign1.getResult().isValid());
    schedule = assign1.getNewState();
    ModifiedState<ScheduleValidator, Schedule> assign2 = schedule.assignStart(StartAssignment.create(
        elevenAM,
        harper142,
        math.getSection(0)));
    assertFalse(assign2.getResult().isValid());
  }

  public void testOverlappingRoomConflictCommutative() {
    // Same test in a different order.
    Schedule.Factory factory = injector.getInstance(Schedule.Factory.class);
    Schedule schedule = factory.create();
    Course origami = getCourse("OrigamiCourse");
    Course math = getCourse("MathCourse");
    ClassPeriod tenAM = getPeriod("10AM");
    ClassPeriod elevenAM = getPeriod("11AM");
    Room harper142 = getRoom("Harper142");
    // Origami is two hours.
    ModifiedState<ScheduleValidator, Schedule> assign1 = schedule.assignStart(StartAssignment.create(
        elevenAM,
        harper142,
        math.getSection(0)));
    assertTrue(assign1.getResult().toString(), assign1.getResult().isValid());
    schedule = assign1.getNewState();
    ModifiedState<ScheduleValidator, Schedule> assign2 = schedule.assignStart(StartAssignment.create(
        tenAM,
        harper142,
        origami.getSection(0)));
    assertFalse(assign2.getResult().isValid());
  }
}
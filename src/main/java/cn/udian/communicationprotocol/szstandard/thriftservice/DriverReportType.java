/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.udian.communicationprotocol.szstandard.thriftservice;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum DriverReportType implements org.apache.thrift.TEnum {
  DETAINED_LICENSE(0),
  DISPUTE(1),
  POLICE(2),
  BLOCKING_LEVEL_1(3),
  BLOCKING_LEVEL_2(4),
  BLOCKING_LEVEL_3(5),
  BLOCKING_LEVEL_4(6),
  CHARGING_OR_FILLING(7),
  MAINTENANCE(8),
  MIDWAY_OBSACLE(9),
  SERVICE(10),
  BUSINESS(11),
  CHARTERED(12),
  CUSTOMIZED_BUS(13),
  HAVE_A_REST(14),
  HAVE_MEAL(15),
  UNOPERATING_OTHER(16),
  TYRE_ISSUE(17),
  BATTERY_ISSUE(18),
  BREAK_ISSUE(19),
  CLUTCH_ISSUE(20),
  WINDOW_ISSUE(21),
  ENGINE_ISSUE(22),
  CARROSSERIE_ISSUE(23),
  OTHER_ISSUE(24),
  HIT_SOMETHING(25),
  SOMEONE_INJURE(26),
  SOMEONE_DEAD(27),
  HIT_VEHICLE(28),
  PASSENGER_INJURE(29),
  OTHER_INJURE(30),
  APPLY_OPERATING(31),
  APPLY_NOT_OPERATING(32),
  APPLY_INTO_LINE_UP(33),
  APPLY_INTO_LINE_DOWN(34),
  APPLY_INTO_STATION(35),
  APPLY_STATION_DISPATCHING(36),
  UNKONW(255);

  private final int value;

  private DriverReportType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static DriverReportType findByValue(int value) { 
    switch (value) {
      case 0:
        return DETAINED_LICENSE;
      case 1:
        return DISPUTE;
      case 2:
        return POLICE;
      case 3:
        return BLOCKING_LEVEL_1;
      case 4:
        return BLOCKING_LEVEL_2;
      case 5:
        return BLOCKING_LEVEL_3;
      case 6:
        return BLOCKING_LEVEL_4;
      case 7:
        return CHARGING_OR_FILLING;
      case 8:
        return MAINTENANCE;
      case 9:
        return MIDWAY_OBSACLE;
      case 10:
        return SERVICE;
      case 11:
        return BUSINESS;
      case 12:
        return CHARTERED;
      case 13:
        return CUSTOMIZED_BUS;
      case 14:
        return HAVE_A_REST;
      case 15:
        return HAVE_MEAL;
      case 16:
        return UNOPERATING_OTHER;
      case 17:
        return TYRE_ISSUE;
      case 18:
        return BATTERY_ISSUE;
      case 19:
        return BREAK_ISSUE;
      case 20:
        return CLUTCH_ISSUE;
      case 21:
        return WINDOW_ISSUE;
      case 22:
        return ENGINE_ISSUE;
      case 23:
        return CARROSSERIE_ISSUE;
      case 24:
        return OTHER_ISSUE;
      case 25:
        return HIT_SOMETHING;
      case 26:
        return SOMEONE_INJURE;
      case 27:
        return SOMEONE_DEAD;
      case 28:
        return HIT_VEHICLE;
      case 29:
        return PASSENGER_INJURE;
      case 30:
        return OTHER_INJURE;
      case 31:
        return APPLY_OPERATING;
      case 32:
        return APPLY_NOT_OPERATING;
      case 33:
        return APPLY_INTO_LINE_UP;
      case 34:
        return APPLY_INTO_LINE_DOWN;
      case 35:
        return APPLY_INTO_STATION;
      case 36:
        return APPLY_STATION_DISPATCHING;
      case 255:
        return UNKONW;
      default:
        return null;
    }
  }
}
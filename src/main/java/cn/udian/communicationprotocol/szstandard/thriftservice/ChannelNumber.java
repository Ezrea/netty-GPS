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

public enum ChannelNumber implements org.apache.thrift.TEnum {
  CHANNELNUMBER_1(0),
  CHANNELNUMBER_2(1),
  CHANNELNUMBER_3(2),
  CHANNELNUMBER_4(3),
  CHANNELNUMBER_ALL(255);

  private final int value;

  private ChannelNumber(int value) {
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
  public static ChannelNumber findByValue(int value) { 
    switch (value) {
      case 0:
        return CHANNELNUMBER_1;
      case 1:
        return CHANNELNUMBER_2;
      case 2:
        return CHANNELNUMBER_3;
      case 3:
        return CHANNELNUMBER_4;
      case 255:
        return CHANNELNUMBER_ALL;
      default:
        return null;
    }
  }
}
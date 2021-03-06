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

public enum FileType implements org.apache.thrift.TEnum {
  FILETYPE_PROGRAM(0),
  FILETYPE_AUDIO(1),
  FILETYPE_LINECONF(2),
  FILETYPE_LINEMAP(3),
  FILETYPE_VIDEO(4),
  FILETYPE_LINETIMETABLE(5);

  private final int value;

  private FileType(int value) {
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
  public static FileType findByValue(int value) { 
    switch (value) {
      case 0:
        return FILETYPE_PROGRAM;
      case 1:
        return FILETYPE_AUDIO;
      case 2:
        return FILETYPE_LINECONF;
      case 3:
        return FILETYPE_LINEMAP;
      case 4:
        return FILETYPE_VIDEO;
      case 5:
        return FILETYPE_LINETIMETABLE;
      default:
        return null;
    }
  }
}

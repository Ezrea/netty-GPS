/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.udian.communicationprotocol.szstandard.thriftservice;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-07")
public class TerminalBasicInfo implements org.apache.thrift.TBase<TerminalBasicInfo, TerminalBasicInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TerminalBasicInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TerminalBasicInfo");

  private static final org.apache.thrift.protocol.TField GPS_BASIC_DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("gpsBasicData", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField TERMINAL_SOFTWARE_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("terminalSoftwareVersion", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TERMINAL_SERIAL_FIELD_DESC = new org.apache.thrift.protocol.TField("terminalSerial", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField HEARTBEAT_INTERAL_FIELD_DESC = new org.apache.thrift.protocol.TField("heartbeatInteral", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField HEARTBEAT_DISTANCE_FIELD_DESC = new org.apache.thrift.protocol.TField("heartbeatDistance", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField LIMIT_SPEED_FIELD_DESC = new org.apache.thrift.protocol.TField("limitSpeed", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TerminalBasicInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TerminalBasicInfoTupleSchemeFactory();

  public GpsBasicData gpsBasicData; // required
  public java.lang.String terminalSoftwareVersion; // required
  public java.lang.String terminalSerial; // required
  public int heartbeatInteral; // required
  public int heartbeatDistance; // required
  public int limitSpeed; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    GPS_BASIC_DATA((short)1, "gpsBasicData"),
    TERMINAL_SOFTWARE_VERSION((short)2, "terminalSoftwareVersion"),
    TERMINAL_SERIAL((short)3, "terminalSerial"),
    HEARTBEAT_INTERAL((short)4, "heartbeatInteral"),
    HEARTBEAT_DISTANCE((short)5, "heartbeatDistance"),
    LIMIT_SPEED((short)6, "limitSpeed");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // GPS_BASIC_DATA
          return GPS_BASIC_DATA;
        case 2: // TERMINAL_SOFTWARE_VERSION
          return TERMINAL_SOFTWARE_VERSION;
        case 3: // TERMINAL_SERIAL
          return TERMINAL_SERIAL;
        case 4: // HEARTBEAT_INTERAL
          return HEARTBEAT_INTERAL;
        case 5: // HEARTBEAT_DISTANCE
          return HEARTBEAT_DISTANCE;
        case 6: // LIMIT_SPEED
          return LIMIT_SPEED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __HEARTBEATINTERAL_ISSET_ID = 0;
  private static final int __HEARTBEATDISTANCE_ISSET_ID = 1;
  private static final int __LIMITSPEED_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.GPS_BASIC_DATA, new org.apache.thrift.meta_data.FieldMetaData("gpsBasicData", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GpsBasicData.class)));
    tmpMap.put(_Fields.TERMINAL_SOFTWARE_VERSION, new org.apache.thrift.meta_data.FieldMetaData("terminalSoftwareVersion", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TERMINAL_SERIAL, new org.apache.thrift.meta_data.FieldMetaData("terminalSerial", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HEARTBEAT_INTERAL, new org.apache.thrift.meta_data.FieldMetaData("heartbeatInteral", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.HEARTBEAT_DISTANCE, new org.apache.thrift.meta_data.FieldMetaData("heartbeatDistance", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LIMIT_SPEED, new org.apache.thrift.meta_data.FieldMetaData("limitSpeed", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TerminalBasicInfo.class, metaDataMap);
  }

  public TerminalBasicInfo() {
  }

  public TerminalBasicInfo(
    GpsBasicData gpsBasicData,
    java.lang.String terminalSoftwareVersion,
    java.lang.String terminalSerial,
    int heartbeatInteral,
    int heartbeatDistance,
    int limitSpeed)
  {
    this();
    this.gpsBasicData = gpsBasicData;
    this.terminalSoftwareVersion = terminalSoftwareVersion;
    this.terminalSerial = terminalSerial;
    this.heartbeatInteral = heartbeatInteral;
    setHeartbeatInteralIsSet(true);
    this.heartbeatDistance = heartbeatDistance;
    setHeartbeatDistanceIsSet(true);
    this.limitSpeed = limitSpeed;
    setLimitSpeedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TerminalBasicInfo(TerminalBasicInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetGpsBasicData()) {
      this.gpsBasicData = new GpsBasicData(other.gpsBasicData);
    }
    if (other.isSetTerminalSoftwareVersion()) {
      this.terminalSoftwareVersion = other.terminalSoftwareVersion;
    }
    if (other.isSetTerminalSerial()) {
      this.terminalSerial = other.terminalSerial;
    }
    this.heartbeatInteral = other.heartbeatInteral;
    this.heartbeatDistance = other.heartbeatDistance;
    this.limitSpeed = other.limitSpeed;
  }

  public TerminalBasicInfo deepCopy() {
    return new TerminalBasicInfo(this);
  }

  @Override
  public void clear() {
    this.gpsBasicData = null;
    this.terminalSoftwareVersion = null;
    this.terminalSerial = null;
    setHeartbeatInteralIsSet(false);
    this.heartbeatInteral = 0;
    setHeartbeatDistanceIsSet(false);
    this.heartbeatDistance = 0;
    setLimitSpeedIsSet(false);
    this.limitSpeed = 0;
  }

  public GpsBasicData getGpsBasicData() {
    return this.gpsBasicData;
  }

  public TerminalBasicInfo setGpsBasicData(GpsBasicData gpsBasicData) {
    this.gpsBasicData = gpsBasicData;
    return this;
  }

  public void unsetGpsBasicData() {
    this.gpsBasicData = null;
  }

  /** Returns true if field gpsBasicData is set (has been assigned a value) and false otherwise */
  public boolean isSetGpsBasicData() {
    return this.gpsBasicData != null;
  }

  public void setGpsBasicDataIsSet(boolean value) {
    if (!value) {
      this.gpsBasicData = null;
    }
  }

  public java.lang.String getTerminalSoftwareVersion() {
    return this.terminalSoftwareVersion;
  }

  public TerminalBasicInfo setTerminalSoftwareVersion(java.lang.String terminalSoftwareVersion) {
    this.terminalSoftwareVersion = terminalSoftwareVersion;
    return this;
  }

  public void unsetTerminalSoftwareVersion() {
    this.terminalSoftwareVersion = null;
  }

  /** Returns true if field terminalSoftwareVersion is set (has been assigned a value) and false otherwise */
  public boolean isSetTerminalSoftwareVersion() {
    return this.terminalSoftwareVersion != null;
  }

  public void setTerminalSoftwareVersionIsSet(boolean value) {
    if (!value) {
      this.terminalSoftwareVersion = null;
    }
  }

  public java.lang.String getTerminalSerial() {
    return this.terminalSerial;
  }

  public TerminalBasicInfo setTerminalSerial(java.lang.String terminalSerial) {
    this.terminalSerial = terminalSerial;
    return this;
  }

  public void unsetTerminalSerial() {
    this.terminalSerial = null;
  }

  /** Returns true if field terminalSerial is set (has been assigned a value) and false otherwise */
  public boolean isSetTerminalSerial() {
    return this.terminalSerial != null;
  }

  public void setTerminalSerialIsSet(boolean value) {
    if (!value) {
      this.terminalSerial = null;
    }
  }

  public int getHeartbeatInteral() {
    return this.heartbeatInteral;
  }

  public TerminalBasicInfo setHeartbeatInteral(int heartbeatInteral) {
    this.heartbeatInteral = heartbeatInteral;
    setHeartbeatInteralIsSet(true);
    return this;
  }

  public void unsetHeartbeatInteral() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEARTBEATINTERAL_ISSET_ID);
  }

  /** Returns true if field heartbeatInteral is set (has been assigned a value) and false otherwise */
  public boolean isSetHeartbeatInteral() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEARTBEATINTERAL_ISSET_ID);
  }

  public void setHeartbeatInteralIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEARTBEATINTERAL_ISSET_ID, value);
  }

  public int getHeartbeatDistance() {
    return this.heartbeatDistance;
  }

  public TerminalBasicInfo setHeartbeatDistance(int heartbeatDistance) {
    this.heartbeatDistance = heartbeatDistance;
    setHeartbeatDistanceIsSet(true);
    return this;
  }

  public void unsetHeartbeatDistance() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEARTBEATDISTANCE_ISSET_ID);
  }

  /** Returns true if field heartbeatDistance is set (has been assigned a value) and false otherwise */
  public boolean isSetHeartbeatDistance() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEARTBEATDISTANCE_ISSET_ID);
  }

  public void setHeartbeatDistanceIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEARTBEATDISTANCE_ISSET_ID, value);
  }

  public int getLimitSpeed() {
    return this.limitSpeed;
  }

  public TerminalBasicInfo setLimitSpeed(int limitSpeed) {
    this.limitSpeed = limitSpeed;
    setLimitSpeedIsSet(true);
    return this;
  }

  public void unsetLimitSpeed() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LIMITSPEED_ISSET_ID);
  }

  /** Returns true if field limitSpeed is set (has been assigned a value) and false otherwise */
  public boolean isSetLimitSpeed() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LIMITSPEED_ISSET_ID);
  }

  public void setLimitSpeedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LIMITSPEED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case GPS_BASIC_DATA:
      if (value == null) {
        unsetGpsBasicData();
      } else {
        setGpsBasicData((GpsBasicData)value);
      }
      break;

    case TERMINAL_SOFTWARE_VERSION:
      if (value == null) {
        unsetTerminalSoftwareVersion();
      } else {
        setTerminalSoftwareVersion((java.lang.String)value);
      }
      break;

    case TERMINAL_SERIAL:
      if (value == null) {
        unsetTerminalSerial();
      } else {
        setTerminalSerial((java.lang.String)value);
      }
      break;

    case HEARTBEAT_INTERAL:
      if (value == null) {
        unsetHeartbeatInteral();
      } else {
        setHeartbeatInteral((java.lang.Integer)value);
      }
      break;

    case HEARTBEAT_DISTANCE:
      if (value == null) {
        unsetHeartbeatDistance();
      } else {
        setHeartbeatDistance((java.lang.Integer)value);
      }
      break;

    case LIMIT_SPEED:
      if (value == null) {
        unsetLimitSpeed();
      } else {
        setLimitSpeed((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case GPS_BASIC_DATA:
      return getGpsBasicData();

    case TERMINAL_SOFTWARE_VERSION:
      return getTerminalSoftwareVersion();

    case TERMINAL_SERIAL:
      return getTerminalSerial();

    case HEARTBEAT_INTERAL:
      return getHeartbeatInteral();

    case HEARTBEAT_DISTANCE:
      return getHeartbeatDistance();

    case LIMIT_SPEED:
      return getLimitSpeed();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case GPS_BASIC_DATA:
      return isSetGpsBasicData();
    case TERMINAL_SOFTWARE_VERSION:
      return isSetTerminalSoftwareVersion();
    case TERMINAL_SERIAL:
      return isSetTerminalSerial();
    case HEARTBEAT_INTERAL:
      return isSetHeartbeatInteral();
    case HEARTBEAT_DISTANCE:
      return isSetHeartbeatDistance();
    case LIMIT_SPEED:
      return isSetLimitSpeed();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TerminalBasicInfo)
      return this.equals((TerminalBasicInfo)that);
    return false;
  }

  public boolean equals(TerminalBasicInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_gpsBasicData = true && this.isSetGpsBasicData();
    boolean that_present_gpsBasicData = true && that.isSetGpsBasicData();
    if (this_present_gpsBasicData || that_present_gpsBasicData) {
      if (!(this_present_gpsBasicData && that_present_gpsBasicData))
        return false;
      if (!this.gpsBasicData.equals(that.gpsBasicData))
        return false;
    }

    boolean this_present_terminalSoftwareVersion = true && this.isSetTerminalSoftwareVersion();
    boolean that_present_terminalSoftwareVersion = true && that.isSetTerminalSoftwareVersion();
    if (this_present_terminalSoftwareVersion || that_present_terminalSoftwareVersion) {
      if (!(this_present_terminalSoftwareVersion && that_present_terminalSoftwareVersion))
        return false;
      if (!this.terminalSoftwareVersion.equals(that.terminalSoftwareVersion))
        return false;
    }

    boolean this_present_terminalSerial = true && this.isSetTerminalSerial();
    boolean that_present_terminalSerial = true && that.isSetTerminalSerial();
    if (this_present_terminalSerial || that_present_terminalSerial) {
      if (!(this_present_terminalSerial && that_present_terminalSerial))
        return false;
      if (!this.terminalSerial.equals(that.terminalSerial))
        return false;
    }

    boolean this_present_heartbeatInteral = true;
    boolean that_present_heartbeatInteral = true;
    if (this_present_heartbeatInteral || that_present_heartbeatInteral) {
      if (!(this_present_heartbeatInteral && that_present_heartbeatInteral))
        return false;
      if (this.heartbeatInteral != that.heartbeatInteral)
        return false;
    }

    boolean this_present_heartbeatDistance = true;
    boolean that_present_heartbeatDistance = true;
    if (this_present_heartbeatDistance || that_present_heartbeatDistance) {
      if (!(this_present_heartbeatDistance && that_present_heartbeatDistance))
        return false;
      if (this.heartbeatDistance != that.heartbeatDistance)
        return false;
    }

    boolean this_present_limitSpeed = true;
    boolean that_present_limitSpeed = true;
    if (this_present_limitSpeed || that_present_limitSpeed) {
      if (!(this_present_limitSpeed && that_present_limitSpeed))
        return false;
      if (this.limitSpeed != that.limitSpeed)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetGpsBasicData()) ? 131071 : 524287);
    if (isSetGpsBasicData())
      hashCode = hashCode * 8191 + gpsBasicData.hashCode();

    hashCode = hashCode * 8191 + ((isSetTerminalSoftwareVersion()) ? 131071 : 524287);
    if (isSetTerminalSoftwareVersion())
      hashCode = hashCode * 8191 + terminalSoftwareVersion.hashCode();

    hashCode = hashCode * 8191 + ((isSetTerminalSerial()) ? 131071 : 524287);
    if (isSetTerminalSerial())
      hashCode = hashCode * 8191 + terminalSerial.hashCode();

    hashCode = hashCode * 8191 + heartbeatInteral;

    hashCode = hashCode * 8191 + heartbeatDistance;

    hashCode = hashCode * 8191 + limitSpeed;

    return hashCode;
  }

  @Override
  public int compareTo(TerminalBasicInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetGpsBasicData()).compareTo(other.isSetGpsBasicData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGpsBasicData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gpsBasicData, other.gpsBasicData);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTerminalSoftwareVersion()).compareTo(other.isSetTerminalSoftwareVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTerminalSoftwareVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.terminalSoftwareVersion, other.terminalSoftwareVersion);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTerminalSerial()).compareTo(other.isSetTerminalSerial());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTerminalSerial()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.terminalSerial, other.terminalSerial);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHeartbeatInteral()).compareTo(other.isSetHeartbeatInteral());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeartbeatInteral()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heartbeatInteral, other.heartbeatInteral);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHeartbeatDistance()).compareTo(other.isSetHeartbeatDistance());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeartbeatDistance()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heartbeatDistance, other.heartbeatDistance);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLimitSpeed()).compareTo(other.isSetLimitSpeed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLimitSpeed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.limitSpeed, other.limitSpeed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TerminalBasicInfo(");
    boolean first = true;

    sb.append("gpsBasicData:");
    if (this.gpsBasicData == null) {
      sb.append("null");
    } else {
      sb.append(this.gpsBasicData);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("terminalSoftwareVersion:");
    if (this.terminalSoftwareVersion == null) {
      sb.append("null");
    } else {
      sb.append(this.terminalSoftwareVersion);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("terminalSerial:");
    if (this.terminalSerial == null) {
      sb.append("null");
    } else {
      sb.append(this.terminalSerial);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("heartbeatInteral:");
    sb.append(this.heartbeatInteral);
    first = false;
    if (!first) sb.append(", ");
    sb.append("heartbeatDistance:");
    sb.append(this.heartbeatDistance);
    first = false;
    if (!first) sb.append(", ");
    sb.append("limitSpeed:");
    sb.append(this.limitSpeed);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (gpsBasicData == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'gpsBasicData' was not present! Struct: " + toString());
    }
    if (terminalSoftwareVersion == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'terminalSoftwareVersion' was not present! Struct: " + toString());
    }
    if (terminalSerial == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'terminalSerial' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'heartbeatInteral' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'heartbeatDistance' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'limitSpeed' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
    if (gpsBasicData != null) {
      gpsBasicData.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TerminalBasicInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TerminalBasicInfoStandardScheme getScheme() {
      return new TerminalBasicInfoStandardScheme();
    }
  }

  private static class TerminalBasicInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TerminalBasicInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TerminalBasicInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // GPS_BASIC_DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.gpsBasicData = new GpsBasicData();
              struct.gpsBasicData.read(iprot);
              struct.setGpsBasicDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TERMINAL_SOFTWARE_VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.terminalSoftwareVersion = iprot.readString();
              struct.setTerminalSoftwareVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TERMINAL_SERIAL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.terminalSerial = iprot.readString();
              struct.setTerminalSerialIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // HEARTBEAT_INTERAL
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.heartbeatInteral = iprot.readI32();
              struct.setHeartbeatInteralIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // HEARTBEAT_DISTANCE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.heartbeatDistance = iprot.readI32();
              struct.setHeartbeatDistanceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LIMIT_SPEED
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.limitSpeed = iprot.readI32();
              struct.setLimitSpeedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetHeartbeatInteral()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'heartbeatInteral' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetHeartbeatDistance()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'heartbeatDistance' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetLimitSpeed()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'limitSpeed' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TerminalBasicInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.gpsBasicData != null) {
        oprot.writeFieldBegin(GPS_BASIC_DATA_FIELD_DESC);
        struct.gpsBasicData.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.terminalSoftwareVersion != null) {
        oprot.writeFieldBegin(TERMINAL_SOFTWARE_VERSION_FIELD_DESC);
        oprot.writeString(struct.terminalSoftwareVersion);
        oprot.writeFieldEnd();
      }
      if (struct.terminalSerial != null) {
        oprot.writeFieldBegin(TERMINAL_SERIAL_FIELD_DESC);
        oprot.writeString(struct.terminalSerial);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(HEARTBEAT_INTERAL_FIELD_DESC);
      oprot.writeI32(struct.heartbeatInteral);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(HEARTBEAT_DISTANCE_FIELD_DESC);
      oprot.writeI32(struct.heartbeatDistance);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LIMIT_SPEED_FIELD_DESC);
      oprot.writeI32(struct.limitSpeed);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TerminalBasicInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TerminalBasicInfoTupleScheme getScheme() {
      return new TerminalBasicInfoTupleScheme();
    }
  }

  private static class TerminalBasicInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TerminalBasicInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TerminalBasicInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.gpsBasicData.write(oprot);
      oprot.writeString(struct.terminalSoftwareVersion);
      oprot.writeString(struct.terminalSerial);
      oprot.writeI32(struct.heartbeatInteral);
      oprot.writeI32(struct.heartbeatDistance);
      oprot.writeI32(struct.limitSpeed);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TerminalBasicInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.gpsBasicData = new GpsBasicData();
      struct.gpsBasicData.read(iprot);
      struct.setGpsBasicDataIsSet(true);
      struct.terminalSoftwareVersion = iprot.readString();
      struct.setTerminalSoftwareVersionIsSet(true);
      struct.terminalSerial = iprot.readString();
      struct.setTerminalSerialIsSet(true);
      struct.heartbeatInteral = iprot.readI32();
      struct.setHeartbeatInteralIsSet(true);
      struct.heartbeatDistance = iprot.readI32();
      struct.setHeartbeatDistanceIsSet(true);
      struct.limitSpeed = iprot.readI32();
      struct.setLimitSpeedIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


package common

object EUserStatus extends Enumeration {
  type EUserStatus = Value
  val _nomarl = Value(0)
  val _blacklist = Value(1)

  def getById(value: String): EUserStatus = {
    value match {
      case "0" =>
        _nomarl
      case "1" =>
        _blacklist
      case _ => _nomarl
    }
  }

  def getById(value: Int): EUserStatus = {
    value match {
      case 0 =>
        _nomarl
      case 1 =>
        _blacklist
      case _ => _nomarl
    }
  }
}

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-db-0.5-jar-with-dependencies.jar!/com/xkeam
 * /database/common/EUserStatus.class Java compiler version: 7 (51.0) JD-Core
 * Version: 0.7.1
 */ 
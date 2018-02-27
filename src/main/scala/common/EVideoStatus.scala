package common

object EVideoStatus extends Enumeration {
  type EVideoStatus = Value
  val _nomarl = Value(0)
  val _hidden = Value(1)
  val _delete = Value(-1)
  def getById(value: String): EVideoStatus = {
    value match {
      case "0" =>
        _nomarl
      case "1" =>
        _hidden
      case _ =>
        _delete
    }
  }
}

/*
 * Location:
 * /home/tamnb/Desktop/xkeam-service-db-0.5-jar-with-dependencies.jar!/com/xkeam
 * /database/common/EUserStatus.class Java compiler version: 7 (51.0) JD-Core
 * Version: 0.7.1
 */ 
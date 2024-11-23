public class studentCourseGrades {
  float percentWeight, studGrade;
  String subType;
  
  public studentCourseGrades(float percentWeight, float studGrade, String subType) {
    this.percentWeight = percentWeight;
    this.studGrade = studGrade;
    this.subType = subType;
  }
  
  public float getPercentWeight() {
    return percentWeight;
  }
  public void setPercentWeight(float percentWeight) {
    this.percentWeight = percentWeight;
  }
  public float getStudGrade() {
    return studGrade;
  }
  public void setStudGrade(float studGrade) {
    this.studGrade = studGrade;
  }
  public String getSubType() {
    return subType;
  }
  public void setSubType(String subType) {
    this.subType = subType;
  }


}

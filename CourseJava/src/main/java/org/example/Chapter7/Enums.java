package org.example.Chapter7;

interface Visitors { void printVisitors(); }
  enum SeasonWithVisitors implements Visitors {
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");

    private final String visitors;
    public static final String DESCRIPTION = "Weather enum";
    private SeasonWithVisitors(String visitors) {
       System.out.print("constructing,");
       this.visitors = visitors;
    }
    @Override public void printVisitors() {
       System.out.println(visitors);
    }
 }
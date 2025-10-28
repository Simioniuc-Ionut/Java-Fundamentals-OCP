package org.example.Chapter7;

public class SealedClass {
}
sealed class Bird {
    public final class Flamingo extends Bird {}
}

sealed class Monkey {}

//class EmperorTamarin extends Monkey {}

non-sealed class Mandrill extends Monkey {}

sealed class Friendly extends Mandrill permits Silly, Moca {}

final class Silly extends Friendly {}
sealed class Moca extends Friendly {}
final class Maka extends Moca{}


abstract sealed class Fish implements Fishable permits Trout, Bass {}
final class Trout extends Fish {}
final class Bass extends Fish {

    public String getType(Fish fish) {
        return switch (fish) {
            case Trout t -> "Trout!";
            case Bass b -> "Bass!";
        };
    }
}

sealed interface Fishable permits Fish{}


package asiankoala.ftc2022.sunmi

enum class State {
    IDLE,
    INTAKING,
    TRANSIT,
    DEPOSITING;
}

enum class Strategy {
    GROUND,
    LOW,
    MED,
    HIGH;
}

enum class Zones {
    LEFT,
    MIDDLE,
    RIGHT,
    WTF
}

enum class Cone {
    NONE,
    LOOKING,
    GRABBED
}
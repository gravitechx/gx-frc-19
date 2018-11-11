Contributing:
=============

1. Code style
2. Unit testing
3. How to author a pull request

Code Style:
-----------

Coding style inherits from [Google Style.](https://google.github.io/styleguide/javaguide.html) Below are changes and amendments specific to the project.

### Constants and units
- All constants ("magic numbers") used throughout code must be declared as `static final` variables to avoid confusion.
- Avoid imperial units at all costs
- If units are used, append a underscore and the standard metric abbreviation breaking constant case:
    + Example: `public double distanceTraveled_m`
    + Example: `public static final double CURRENT_LIMIT_A`

### Structure

The code base is structured for extendability. All code must follow a strict structure be be integrated into the main branch. See below for more information.

- `com.team6619.frc2019.io`: Contains controller io and smartdashboard related classes.
- `com.team6619.frc2019.subsystems`: A subsystem is a partition of a robots function and contains one main class.
- `com.team6619.frc2019.util`: Contains utility or helper functions for the robot. All functionality in util *should be unit tested*

### Commenting

Commenting and documentation is required. [See Oracle Code Conventions.](https://www.oracle.com/technetwork/java/codeconventions-141999.html#385)

Unit Testing:
-------------

__Unit tests are required to commit changes.__ Junit is used for testing. Mock when appropriate. 

Exceptions to unit testing:
- DTO (Data Transport Objects)
- Hardware bound functionality

Interacting With Github:
------------------------

- __Proposing A feature:__ Open an issue with a detailed description of the feature and the motivation behind it. Label it feature.
- __Bug:__ You should check to see if your bug reported. Open an issue with a detailed description of the bug. Label it bug. If you have fixed it, link it to the corresponding pull request. __If it has a possible of resulting in a dangerous hardware failure, label it BLOCKING__ 
- __Refactor:__ Describe the refactor appropriately and add the motivation. Label it enhancement.
- __Contributing:__ Fork the repository and author a pull request. This starts a mandatory review.

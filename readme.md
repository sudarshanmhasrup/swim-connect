### How to read the source code?
Before you start thinking about the source code, let's first understand how this project is
structured. Having a multi-module project structure was not really necessary for this project, but
following this practice makes reading, maintaining, and modifying the project easier. Also, you may
notice that some pages, e.g., `Child Details Page` or similar pages that require a lot of code, are
present as modules in this project. Now, let's talk about the functionality of each module.

`Note`: The order in which the modules are listed here is the order in which they should be viewed.  
This order can also be seen in the module declarations in `settings.gradle.kts`.

#### Modules used in this project
1) `mobileApp`.
2) `composeAppTheme`.
3) `shared`.
4) `childDetails`.
5) `discoverDevices`.

#### Information about each module
- `mobileApp`: This is the main module and serves as the starting point of the project. It contains
  all the source code required to target both `iOS` and `Android` mobile apps.
- `composeAppTheme`: This module acts as a library for the project. Since the project has a custom
  design system implementation, all the necessary theming `APIs` are provided by this module.
- `shared`: This module contains common components such as `Primary Button`, `Primary
  User Input Field` and many more. It acts as a library for all modules that make use of these
  components. But it's not limited to UI components only. It also contains extension `modifiers` and
  `keyboard actions`, which are used in multiple modules. This module centralizes management of
  components, extensions and utility functions.
- `childDetails`: This module contains all the source code required for collecting the child's
  basic information. It contains all required logic and UI components for collecting and saving basic
  information of the child. `ChildDetailsPage` is declared as a module because it contains a little
  bit of Extra logic so declaring it as a module makes it easy to manage the source code.
- `discoverDevices`: This module contains all the source code required for discovering and
  connecting to nearby available `SwimConnect` devices. It also contains all the UI components
  required for discovering and connecting to devices including `Missing Permission Dialogue UI` and
  all required pages.

### Prototype screenshots

<img alt="Prototype flow container 1" src="./assets/flow_container_1.svg">
<img alt="Prototype flow container 1" src="./assets/flow_container_2.svg">
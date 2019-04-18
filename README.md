# EndersAdditions

## The best plugin!
We all know and love EssentialsX, but it sorta lacks integration and order. With BukkitBasics you are immediately aware of customization via lang.txt and the ability to change options via config.yml. It even includes native integration with GroefPrevention and SavageFactions!

## That's cool bro, but how do I compile it?  
Simple! ([Eclipse](https://www.eclise.org))  
**1)** Open a new workspace.  
**2)** Drop the BukkitBasics source into that folder.  
**3)** Right click the project explorer and select Import.  
**4)** Under maven select Existing maven project.  
**5)** Follow through the import procedure.  
**6)** Open a CMD window, (Or powershell and run cmd)  
**7)** Download Xcore from [here](https://github.com/Xpulse/Xcore)  
**8)** Do this command (you need maven installed) `mvn install:install-file -Dfile=<path-to-xcore> -DgroupId=com.github.xcore -DartifactId=xcore -Dversion=1.1.0 -Dpackaging=jar`.  
**9)** Whether via Eclipse or maven do "maven clean" followed by "maven install"  
**10)** The jar will be in /target with the name "bukkitbasics-<version>-included (It's very important to use the one labeled as "included")  

## Integrating with bukkit basics
TODO

## Links
TODO

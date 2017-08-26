## Toolings

For being proficient in software development, you'll need to master a myriad of different technologies, tools, frameworks and processes, this chapter talks about some of them.

### Overview

Like mentioned in the chapter about [software engineering](../softwareengineering/index.html), developing software is a highly social activity yet technically demanding. Writing correct, performant, maintainable software is supported by a variety of tools and processes.

As a professional software developer, you have to be aware of following concepts:

- Continuous Integration
- Version Control Systems
- Repository Management Systems
- Build Systems
- Ticket Systems
- IDE's
- Communication systems (Slack, Gitter, IRC, Skype, XMPP based chats ...)
- Social media
- ...  

This list could be arbitarily prolongated, we'll stop with those mentioned above. Be aware that this list only comprises toolings a developer will need to do his actual work, which is to code features into a program or to refactor it. This domain has of course its own challenges, of which we will talk later on.

### Continuous Integration

Continuous Integration (CI ) means that the work of single developers of an organisation are unified and merged into a single repository in a periodic manner. The integration involves compiling the code, executing automated tests and the building of artefacts ready to be used by an end user. 

CI improves the "time to market" factor when creating software, and generally helps in improving the quality of the generated artefacts. However, it can also slow down the delivery of certain parts of the software, since every commit has to fulfill at least a common denominator with the rest of the code (the lowest would be that the compilation of the software is still functional). CI will execute automated tests, which serve as a metric of 'correctness'. To quickly evaluate if everything is ok a certain amount of reporting of the results of the compilation of all software parts as well as the results of the executed tests has to be in place. If you want to have fast turnaround times and a high productivity in the software engineering department, it is also vital that the compilation of the program artefacts is fast, the construction of the deployment artifacts is automated and that your tests can be executed concurrently and without interference between each other. 

A discipline which builds on CI is 'Continuous delivery', which means that software artefacts are served to the actual user. That means that a change in the software has an instant effect on the users. If you think about this, you will certainly agree that this imposes great demands on the systems in respect to quality and robustness and thus the goal of real continuous delivery is reached by just a few in the industry. In our so called 'internet time' and in some domains however this approach is vital for the success of a business and as such one corner stone of the success of real 'high tech' companies.

### Version Control Systems

Version Control Systems (VCS)s are of great help when developing software. It is not exaggerated to say that they are of paramount importance.

VCSs help in organizing source code, tracking changes throughout time, isolating individual development and merging them together in a single branch of development. VCSs make clear which developer created which feature, they are bookkeeping tools for the work of software developers. 

VCSs support working on different copies of the same source code, which is vital for concurrent feature development. VCS's are the foundation of release management and the 'safe harbour' if some disaster happened.

Typically, VCSs are integrated in IDE's, but they can be used as standalone tools as well and as such support an individual workflow which is not bound to a certain product or vendor.

One example of an VCS is the GIT versioning control system which we will make use of in our course. GIT is the de- facto standard of VCSs in use today. It is fast, reliable and full of features which satisfy almost every requirement one could think of. All big companies have endorsed GIT in the meantime. It is a success story much like Linux was, and interestingly enough GIT is another project started originally by Linus Torvalds, the father of Linux. 

 
### Repository Management Systems

One aspect of software engineering is how to distribute the work. For pure source code, we already know that VCSs are used to collaborate on projects. Source code is distributed on more than one physical locations using those systems. For the final artifacts however, other systems are in use.

One could simply use a fileshare to distribute binaries. More practical on a global scale are web servers which can serve binary, static content like if they would have been build for this task ;-). Add some authentication and a standardised structure and lookup mechanism, and you've got all you need for a Repository Management System (RMS).

RMS distribute the binary artifacts, and this in an efficient and integrated way. Again, standardization is key to success, a defined protocol for transportation and a standard lookup algorithm have been a key factor for the huge success of Java in the enterprise world. 
 
In fact, the contribution of a standardized addressing of artifacts paved the way for reusing software on a global scale. RMS like Artifactory or Nexus are key factors for a efficient IT infrastructure. Their success inspired other technologies as well, like for example .NET with the nuget tooling.

 
### Ticket Systems

Ticket Systems (TS) - speaking in the context of the IT industry -  are toolings which help to organize and describe the actual work which has to be done by a developer or a software team. In short, they provide custom tailored TODO lists for every developer, and work can be tracked on a higher level of abstraction (as opposed of VCSs for example). Typically, developers and their project leaders use a ticket system to organize work. A whole industry has been thriving around this topic, one well known incarnation of a ticket system would be the JIRA tool suite by Attlassian.

Ticket systems help in prioritizing work, logging work, resource allocation, discussing certain details of issues and of course in defining issues themselves.

### Build Systems

Build Systems help to assemble software components and interact with VCS and RMS in order to fully automate the creation of software artifacts. Once properly configured, the construction of the whole project is their business. Software companies can only be successful if they suceed in automating most of their work, such that their employees can emphasize on creating new features, new products. 

Every day a massive amount of new software is written, existing software is changed and old one is being wiped out if it is not used anymore. In this constant flow of change there has to be a certain set of rules which define how software components are being built, how they are assembled together, and how they are properly configured. This is the task of the build system.

A build system can make a dramatic difference in the productivity of a company, and a good build system is vital for being competitive in todays ever changing software world.

Examples of build systems are 

|Name      | Environment | Description |
|----------|-------------|-------------|
|ANT       | JVM         | One of the first build tools on the JVM |
|Maven     | JVM         | De facto standard build tool for Java based projects nowadays. Paved the way for all successors and layed out standard conventions. |
|Gradle    | JVM         | Some see it as the successor of Maven, currently build tool for Android. Not as XML heavy as Maven, powerful DSL |
|Leiningen | JVM         | Build tool for Clojure. |
|SBT       | JVM         | Build tool for Scala ("Simple Build Tool") |
|Pants     | JVM         | Build tool developed by Twitter, young with fresh approaches|
|Make      | C / C++     | The father of all build tools |
|CMake     | C / C++     | Superset of features of Make, cross platform discovery of system libraries|
|MsBuild   | .NET        | by Microsoft, helps building .NET for example |
|...       | ...         | ...|

to name but a few. Most of the cited list are JVM based build systems, but for other technolgies there exist such concepts as well.  
  
### Communication Systems

Unlike common belief software development is a highly interactive activity. This means that it is vital that communication is at the center of development. It is again important to keep communication effective, and this is a skill  which is a very valuable asset.

Again, there exists a plethora of communication tools which are designed to help in this field. Nowadays it is normal to use Facebook Messenger or Skype to talk to friends, originally the predecessors of such applications where used by software geeks to talk (mostly) about programming ;-).

Like in the older days it is equally important now to master those tools, to be precise in communicating the problem at hand. Most of the time, a company will use a standardized system such that such conversations are saved somewhere and can be searched later for valuable information. 

Chat systems are used frequently also not to disrupt software developers from their current task, and one has to practice asynchronous communication like any other skill. It takes some time until such tools are used in a productive, focused way.

### Social media

One should not underestimate the impact of social media for programming. Like in any other field of work, also in software engineering there are certain things which come and go, something is hot today and is out of fashion tomorrow. 

Influencer like known in the youtube generation existed already decades ago in the computer industry. 

Used in the right way, social media can be very helpful in gathering a professional network which can help in certain situations. New trends, techniques and views are likely to find first in social media. 

Lastly, social media is also important to distribute own work and to connect to interesting people. Used in the right way, social media can be very important for your career as software engineer.


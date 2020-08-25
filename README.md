# Data Analyser

This project is a data analysis system that imports many flat files, reads, analyzes the data and generates a report. The analysis is
executed every time a file is made available in the input directory.

Below is a diagram of the overall system architecture:

![Data Analyser Architecture](data-analyser-arch.png?raw=true "Data Analyser Architecture")

### Flat input file layout
There are 3 kinds of data inside those files. For each kind of data there is a different layout.

 - Salesman
    - 001çCPFçNameçSalary

 - Customer
    - 002çCNPJçNameçBusiness Area
 - Sales
    - 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

The input file must be found in `%HOMEPATH%/data/in`

### Flat output file data
 - Amount of clients in the input file
 - Amount of salesman in the input file
 - ID of the most expensive sale
 - Worst salesman ever

The output file must be found in `%HOMEPATH%/data/out`

## Implementation
### Technology
This project was developed under the SpringBoot framework, using Gradle as dependency and build management. 
To reduce boiler plate code, the lombok library was used.

### Solution
The execution flow for this solution happens as follows:
  - A file arrives in the input directory located in `%HOMEPATH%/data/in`.
- Through a Cron job that periodically checks the input directory, the service detects the presence of the file.
- Immediately begins reading and extracting the contents of the file.
- An individual report for the file, similar to the summary report with all files, is generated and saved in `%HOMEPATH%/data/out/individual-reports`
- The file is moved to the processed folder, located in `%HOMEPATH%/data/processed`
- The contents of all files already processed are read and loaded to recalculate and generate the summary report containing the output information for all the combined data.

To meet the challenge so that the output data has its maximum utility, a strategy was created so that in addition to the
summary report of all the data present in all the combined files, an individual report is also generated for each file, 
considering only its scope. This adds value for potential users who want to map information present in certain specific 
files.
To this end, a subdirectory was created within the standard output directory, called individual-reports, containing the 
individual report for each input file, with a name prefixed by it, and postfixed by the output extension done.out




### An alternative solution
To meet the need proposed by the challenge, it is necessary to consider a very important factor mentioned: for each new
file made available, however small, it implies recalculating the requested data, also considering all previously processed
input data. If left untreated, this factor can become an issue as the volume of input data increases. To try to minimize
this, it would be possible to consider the summary of each individual file containing the most relevant information, which
would serve for the final calculation. Thus, with each new arrival file, only the analysis of the new files and the
recalculation based on the summary of the others to generate the totalizing report would need to be performed, which would
give a drastic gain in a scenario with multiple large files.

But, for this solution to be valid, it must be agreed that the input files will have self-contained information, that is,
any and all information from an entity must be contained in the same file, meaning that all sales by a seller should be
in the same file, for example. This would limit the scope of use that the system could meet in terms of variability of
input data, but would add a performance gain due to the factors already mentioned.
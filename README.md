# DopCHECK
## Folder Structure
```
├── README.md
├── LICENSE.md
├── INSTALL.md
├── DPC_Issue_Detection
│   ├── generate_test_case
│	    ├── gen_test_case.py
│	    ├── in_context_samples.txt
│	    ├── key.txt
│	    ├── text_case_example(android 10)
│   ├── test_case_apk
├── DPC_ontology_extraction
│   ├── Privacy changes in Android 10_Android Developers.html
│   ├── extract_DPC.py
```
***Note:*** This tree includes only main files. 

## Description:
Below we describe each main file in our folder below.

### DPC_Issue_Detection
```gen_test_case.py```: To generate test cases using the GPT-4 API, incorporating in-context learning, it's necessary to replace the third and seventh lines with your personal OpenAI API key. To apply for an API key, please visit https://openai.com/product.

```in_context_samples.txt```: Include five manually written test cases for use in the in-context learning process of ```gen_test_case.py```.

```key.txt```: Record OpenAI API key.

```text_case_example(android 10)```: Record ten examples of test cases returned by GPT-4 in Android 10.

```text_case_apk```: Using test cases to generate an APK. Test cases with the same configuration environment are placed under the same APK. The source code path for the test case is: app -> src -> main -> java -> com -> example -> MainActivity.java.

### DPC_ontology_extraction
```Privacy changes in Android 10_Android Developers.html```: Android 10 privacy change documentation.

```extract_DPC.py```: Running this file can extract the DPC from the Android 10 privacy change documentation, then analyze its entities and identify the subsumptive relationships between these entities. The results are printed on the console for easy display."

### Initial app interface
<!-- ![image](https://github.com/YanChuan390/DopCHECK/blob/main/Initial%20app%20interface.png) -->
<img src="https://github.com/YanChuan390/DopCHECK/blob/main/Initial%20app%20interface.png" width="400px">




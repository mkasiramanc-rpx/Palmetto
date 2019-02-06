
# How to calculate Coherence Scores:

### Different Metrics for evaluating the performance of the Model:

- UMASS
- UCI
- NPMI
- C_A
- C_P
- C_V

### Preprocessing of data:

- Create a Lucene Index, using LuceneIndexCreater.java file.

- The parameters to the file is <"index-path">  <"source-file">

- index-path : where to create the lucene index.

- source-file: a "txt" file that contains all the documents.

- These documents has to be preprocessed, the following steps has to be done before creating the index:
    - Tokenized.
    - Stopword Removed.
    - Stemmed or Lemmatized.
    - Replace the spaces in bi/trigram with underscores.
    - Each words has to be space seperated.
    - Each documents has to be line seperated.


- Topic file should be in the form of :
    - Space replaced with underscores in vocabs.
    - commas replaced with spaces in documents.
    - Documents seperated by line.

### Calculating Coherences

- Use java org.aksw.palmetto.Palmetto <"index-path"> <"calc_type"> <"topic-file">

- Which produces a file inside index-path directory with calc_type suffix.

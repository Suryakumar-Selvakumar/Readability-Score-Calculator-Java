# Readability Score Calculator

This project is an implementation of a console-based tool to analyze the readability of a text. It evaluates text
difficulty using well-known readability metrics and estimates the age group that can comprehend the text. The program
was built incrementally, with each stage adding new functionality:

1. **Stage 1 - Simplest estimation:**  
   Implemented a basic check where any text longer than 100 characters was considered *hard*, and otherwise *easy*.

2. **Stage 2 - Words and sentences:**  
   Improved readability analysis by calculating the average number of words per sentence. If the average exceeded 10,
   the text was marked as *hard*, otherwise *easy*.

3. **Stage 3 - Score!:**  
   Introduced the **Automated Readability Index (ARI)** formula to provide a numerical readability score and
   corresponding age range. The program also started reading text directly from a file passed via command line
   arguments. Word, sentence, and character counts were displayed alongside the score.

4. **Stage 4 - More parameters:**  
   Extended the tool with additional readability metrics:
    - **Flesch–Kincaid readability tests (FK)**
    - **Simple Measure of Gobbledygook (SMOG)**
    - **Coleman–Liau index (CL)**  
      Added syllable and polysyllable counting to support these formulas. The program allowed users to calculate any one
      score or all at once, and if all were selected, it also printed an average age estimate.

## Demo

<video width="1920" height="1080" align="center" src="https://github.com/user-attachments/assets/4c577544-09c1-4523-87cd-24d7c34f51c1"></video>


## Takeaway

This project bolstered my ability to work with file input, text parsing, and string processing in Java. Implementing
multiple readability metrics gave me experience translating mathematical formulas into working code and handling edge
cases like syllable counting. It also deepened my understanding of how small linguistic features, such as sentence
length and syllables, affect readability. By the final stage, I had built a flexible analysis tool capable of applying
several different algorithms to the same text, which strengthened my confidence in designing modular and extensible
programs.

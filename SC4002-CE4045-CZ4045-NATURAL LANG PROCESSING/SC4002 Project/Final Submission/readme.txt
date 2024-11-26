SC4002 Natural Language Processing | 2024-2025 Assignment
Group No. 57
Project Overview
This project develops a sentiment classification system using pretrained word embeddings like GloVe, to classify sentences based on sentiment expressed. This README details how to set up and run the codebase for the assignment.


File Structure
grp57_assignment.ipynb: Jupyter notebook containing the complete code, including data preprocessing, model training, and evaluation.
Report.pdf: Detailed project report explaining the methodology, experiments, and findings.

Installation
Ensure Python 3.8 or higher is installed.
Install required Python packages:
pip install nltk numpy datasets tokenizers tqdm matplotlib torch afinn scikit-learn transformers
Download 'glove.6B.100d.txt' from https://www.kaggle.com/datasets/danielwillgeorge/glove6b100dtxt. Ensure that it is placed in the same working directory as the notebook.

Running the Code
Open the grp57_assignment.ipynb notebook in Jupyter Lab or similar environment.
Run all cells sequentially to reproduce the results as discussed in the report.
Detailed explanations and intermediate results are commented within the notebook for clarity.

Expected Outputs
The notebook will display the training and validation Losses for each model as graphs.
Final accuracy scores on the test dataset are printed at the end of each model training.

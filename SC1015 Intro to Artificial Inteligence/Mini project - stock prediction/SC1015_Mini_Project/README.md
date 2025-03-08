About
========================
[![Bull vs Bear][project-screenshot]](https://www.captiveinternational.com/media/image/shutterstock_1695151366_na_studio.jpg)

<div id="top"></div>

## Motivation
Recently, there has been a increase in the interest in stock market, with the abundance of trading platform such as [MooMoo](https://www.moomoo.com/sg/) and [Tiger broker](https://www.tigerbrokers.com.sg/), stock market has been increasingly attractive to consumers. 

This has lead to a boom in the number of retail investors, causing the sudden fluctuation and movement of several stocks such as:
 * [Gamestop ($GME)](https://finance.yahoo.com/quote/GME/)
 * [AMC Entertainment Industry($AMC)](https://finance.yahoo.com/quote/AMC?p=AMC&.tsrc=fin-srch)
 * [Tesla($TSLA)](https://finance.yahoo.com/quote/TSLA?p=TSLA&.tsrc=fin-srch)

In addition of a ever-decreasing bank return interest rate, zero interest rate for the entry of investing and high return rate for investment of stock, it have captured the attention of the public.

<p align="right">(<a href="#top">back to top</a>)</p>

### Problem Definition

Therefore, it seems like the inclusion of IT sector in the Financial industry will be upcoming and rising. By riding upon the current trend with the increase popularity of stock, we have narrowed down a particular problem which have frustrated many institutional investors and retail investors.


Hence, this project aims to predict future movement of a stock through some factors such as:
 * Past data of stock
 * News

We have chosen Apple stock (**AAPL**) as a sample to test our method for analyzing and 
predicting the movement of the market. 

<p align="right">(<a href="#top">back to top</a>)</p>

## Goal and Requirement
### Goal
Our goal is to predict stock price movement by using machine learning (**ML**) concepts.
To analyze the best concept to predict the movement of stock and test 
the accuracy of the model. 
### Criteria
In order to attain our goal, these criteria have to be met first.
* Select suitable variables to predict the movement of the stock price.
* Select suitable ML concepts to best predict the movement. 
  1. Time Series
  2. Classification
  3. Neural Network
* Compare and test the accuracy of the model

<p align="right">(<a href="#top">back to top</a>)</p>

### Key Learning
 * Time Series
  * AutoRegressive Integration Moving Average & Generalized AutoRegressive Conditional Heteroskedasticity (ARIMA-GARCH) Model
* Classification
  * Decision Tree Model
* Neural Network
  * Recurring Neural Network (RNN)
    * Long Short Term Memory (LSTM)
  * Natural Language Processing (NLP)    
<p align="right">(<a href="#top">back to top</a>)</p>

## Procedures
Here are the documentation for the procedures for our project.
1. [Data Preparation](Docs/data_prep.md)
2. [Exploratory Anaylsis](Docs/exploring_analysis.md)
3. [Algorithmic Optimization](Docs/algo_opt.md)
4. [Conclusion](Docs/conclusion.md)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

In order to run this project, there are several prerequisites to be installed.

### Prerequisites

Installation of Python,pip and Jupyter have to be done. 
Download [python version &gt;3.8 ](https://www.python.org/downloads/)

1. Verify that python is installed properly into your computer.
    ```sh
    py --version
    ```
2. Next, check the status of pip. Skip to step 4 if your version of pip is newest.
   ```sh
   py -m pip --version
   ```
3. To upgrade your pip, run this command.
   ```sh
   py -m pip install --upgrade pip setluptools wheel
   ```
4. Install Jupyter Notebook with:
   ```sh
   pip install notebook
   ```
If faced any error, refer to [Troubleshooting documents for PIP & Python](https://packaging.python.org/en/latest/tutorials/installing-packages/)

### Installation

1. Clone the repo
   ```sh
   git clone Hendyley/SC1015_Mini_Project.git
   ```
2. Run this project in your Jupyter Notebook
   ```sh
   jupyter notebook
   ```

If faced any error, refer to [Troubleshooting Documentation for Jupyter](https://jupyter-notebook.readthedocs.io/en/stable/troubleshooting.html)
<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

These sources are used for the research of this project.

* [Complete Guide to Time-Series Analysis](https://towardsdatascience.com/the-complete-guide-to-time-series-analysis-and-forecasting-70d476bfe775?gi=81cbcf49cf28)
* [Guide for ARIMA forecasting](https://towardsdatascience.com/machine-learning-part-19-time-series-and-autoregressive-integrated-moving-average-model-arima-c1005347b0d7)
* [Research for ARIMA-GARCH model for Stock Price Prediction](https://www.e3s-conferences.org/articles/e3sconf/pdf/2021/68/e3sconf_netid21_02030.pdf)
* [What is ARCH and GARCH model?](https://machinelearningmastery.com/develop-arch-and-garch-models-for-time-series-forecasting-in-python/)
* [Stock Prediction for LSTM](https://www.datacamp.com/community/tutorials/lstm-python-stock-market)
* [Time Series Decomposition](https://towardsdatascience.com/time-series-from-scratch-decomposing-time-series-data-7b7ad0c30fe7)
* [Introduction to LSTM](https://machinelearningmastery.com/gentle-introduction-long-short-term-memory-networks-experts/)
* [Sentimental Analysis using Vader](https://towardsdatascience.com/sentimental-analysis-using-vader-a3415fef7664#:%7E:text=VADER%20).
* [ARIMA Definition](https://www.investopedia.com/terms/a/autoregressive-integrated-moving-average-arima.asp)
* [Introduction to Sentiment Analysis](https://www.lehnerinvestments.com/en/sentiment-analysis-stock-market-sentiment/)
* [Sentiment Analysis on Stock Market](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC8053016/)
* [Factors to affect Stock Market Movement](https://www.investopedia.com/articles/basics/04/100804.asp)
* [Introduction to Stacked LSTM](https://www.icst.pku.edu.cn/struct/Projects/multitask_OAD.html)
* [What is Recurrent Neural Network](https://www.ibm.com/cloud/learn/recurrent-neural-networks)

### Usage of Third-Party Application
* [RapidAPI Search](https://rapidapi.com/newscatcher-api-newscatcher-api-default/api/google-news)
* [VADER](https://github.com/cjhutto/vaderSentiment)
* [Kaggle](https://www.kaggle.com/datasets/meetnagadia/apple-stock-price-from-19802021)
* [Natural Langauge Toolkit (NLTK)](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC8053016/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[project-screenshot]: https://www.captiveinternational.com/media/image/shutterstock_1695151366_na_studio.jpg

### Contributors
- `ZlLow` - Documentation, Time Series, Neural Network, Implementation
- `Hendyley` - Neural Network, Classification, Data Preparation 
- `nicholassy` - Video Editing, Time Series, Data Analysis

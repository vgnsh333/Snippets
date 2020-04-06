# -*- coding: utf-8 -*-
"""
Created on Mon Apr  6 11:35:18 2020

@author: Vignesh

"""

from bs4 import BeautifulSoup

f = open("follower.html", "r",encoding="utf8")
d = f.read()
#print(f.read()) 

soup = BeautifulSoup(d, 'html5lib') 
#print(soup.prettify()) 
table = soup.find('div', attrs = {'class':'PZuss'}) 
#Pzuss is the container for the list which pops up
#print(table)
list1 = set()
for row in table.findAll('a', attrs = {'class':'FPmhX notranslate _0imsa '}):
    #print(row['title'])
    list1.add(row['title'])

print(len(list1))

f1 = open("following.html", "r",encoding="utf8")
d1 = f1.read()
#print(f.read()) 

soup = BeautifulSoup(d1, 'html5lib') 
#print(soup.prettify()) 
table = soup.find('div', attrs = {'class':'PZuss'}) 

#print(table)
list2 = set()
for row in table.findAll('a', attrs = {'class':'FPmhX notranslate _0imsa '}):
    #print(row['title'])
    list2.add(row['title'])

print(len(list2))

print(list2 - list1)
print(len(list2 - list1))

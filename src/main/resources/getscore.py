# -*- coding:UTF-8 -*-
"""
Created on Tue Apr 17 16:21:58 2018

@author: bing
"""


# this is a python program for scrawl the all semester grade in UESTC  
# please filing the username and the password  
# finish date: April 4th, 2016 
import sys
import datetime
import re
import urllib  
import  http.cookiejar 
from bs4 import BeautifulSoup
import os
import webbrowser
def GetMiddleStr(content,startStr,endStr):
    patternStr = r'%s(.+?)%s'%(startStr,endStr)
    p = re.compile(patternStr,re.IGNORECASE)
    m= re.findall(p,content)
    for item in m:
        return item
def DelLastChar(str):
    str_list=list(str)
    str_list.pop()
    return "".join(str_list)
def Start(username,password):
    currentday = datetime.datetime.now().day
    middle = currentday+24
    jizhunBinky = str(currentday)+'22'+str(middle)+'43'
    #grade_htmlfile = 'grade_all.html'
    string = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"
    #String_cookie='ASP.NET_SessionId=z0xuou45vogazd55jc5o1q55; ASPSESSIONIDSQADTDCC=DGEPLEKABIBOBKBAAJGAFJMG; ASPSESSIONIDSSCASDDD=HLKNLFKALFPBGNMMEMNKEIJF; ASPSESSIONIDCSQBBSTB=DELHHMEBINPLGJOOGDPHAAJH; ASPSESSIONIDCQRDATTA=DIGPLLEBCEMLILFGKEPCHNGH; userint=Y30150649'
    headers = {'User-Agent' : string}
    # all_gradefilename = 'grade_all.txt'
    # all_gradefile = open(all_gradefilename, 'w')
    # grade_html_open = open(grade_htmlfile, 'w')
    cookie = http.cookiejar.MozillaCookieJar()

    opener = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(cookie))
    postdata_raw = {'__VIEWSTATE':'', 'username':username, 'password':password, 'btnlogin':''}
    # print postdata, type(postdata)
    loginUrl = 'http://59.78.108.51/webui/'
    #print(loginUrl)
                # first, get the lt and execution of the HTML
    try:
        first_result = opener.open(loginUrl)
    except urllib.error.HTTPError as e:
        print(e.code)
        print(e.reason)
       # print("===urlerror======")
    html = first_result.read().decode('utf-8')
    #print(html)
   # soup = BeautifulSoup(html, 'html.parser', from_encoding='utf-8')
   # print(soup)
    #print (soup.find_all('input'))

    viewstate_string=GetMiddleStr(html,"name=\"__VIEWSTATE\" value=\"","\" />")
    postdata_raw['__VIEWSTATE'] = viewstate_string

    btnlogin_value = GetMiddleStr(html,"name=\"btnlogin\" value=\"","\" onclick")
    postdata_raw['btnlogin'] = btnlogin_value
    postdata = urllib.parse.urlencode(postdata_raw).encode(encoding='UTF8')
    #print(postdata)

    request_url = "http://59.78.108.51/webui/login.aspx"
    request_new = urllib.request.Request(request_url, headers=headers)
    #print loginUrl_success
    try:
        opener.open(request_new, postdata)

    except urllib.error.HTTPError as e:
        print(e.code)
        print(e.reason)
    #print(response.geturl())
    #print(response.info())

    temp_url='http://59.78.108.51/webui/index_std.aspx'
    temp_url_new = urllib.request.Request(temp_url, headers=headers)
    response_temp = opener.open(temp_url_new)
   # cookie.save(ignore_discard=True, ignore_expires=True)
    temp_html=response_temp.read().decode('utf-8')
    #print(temp_html)
    if not temp_html:
          print ("2")
    last_string=GetMiddleStr(temp_html,'info_liststd.asp?','DeptID')
    if last_string is None:
        return "2"
    #print('==========================')
    #print(last_string)
    values={}
    userid=GetMiddleStr(last_string,'UserID=','&UserType')
    usertype=GetMiddleStr(last_string,'usertype=','')
    identity=int(userid)-42439+int(jizhunBinky)
    values['userid'] = userid
    values['deptid']='3'
    values['identity']=str(identity)
    values['usertype']=usertype
    #print(fenjie[4])

    data_fenjie = urllib.parse.urlencode(values)
    #print("============"+data_fenjie)
    grade_all_url="http://59.78.108.51/webxs/webxs_kccjPrint.asp"+'?'+data_fenjie
    #print('==========================')
    #print(grade_all_url)
    #grade_all_url = 'http://eams.uestc.edu.cn/eams/teach/grade/course/person!historyCourseGrade.action?projectType=MAJOR'
    grade_all_result = opener.open(grade_all_url)
    grade_all_html = grade_all_result.read().decode("gb2312", 'ignore')
    return grade_all_html
last_html=Start(sys.argv[1],sys.argv[2])
print(last_html);
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.service import Service
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.action_chains import ActionChains
import time

driver = webdriver.Chrome()
driver.maximize_window()
driver.implicitly_wait(10)

driver.get("http://testautomationpractice.blogspot.com/")
print(driver.title)
print(driver.current_url)
print(driver.current_window_handle)

#VOLUNTEER SIGN UP

driver.switch_to.frame("frame-one1434677811")
#First_name
first_name = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-1']")
first_name.send_keys("kevin")
print("first name is displayed:",first_name.is_displayed())


#Last_name
last_name = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-2']")
last_name.send_keys("durant")
print("last name is displayed:",last_name.is_displayed())

#phone
phone = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-3']")
phone.send_keys("957247")
print("phone is displayed:",phone.is_displayed())

#Country
country = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-4']")
country.send_keys("INDIA")
print("country is displayed:",country.is_displayed())

#City
city = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-5']")
city.send_keys("Jamnagar")

#Email_address
email = driver.find_element(By.XPATH,"//input[@id='RESULT_TextField-6']")
email.send_keys("abc.@gmail.com")
print("email is displayed:",email.is_displayed())

#RADIO_BUTTON
male = driver.find_element(By.XPATH,"//label[@for='RESULT_RadioButton-7_0']")
male.click()
print("radio button is selected:",male.is_selected())

#Best time to Contact --> ----- DROPDOWN ELEMENT ------
best_time_to_contact = Select(driver.find_element(By.XPATH,"//select[@id='RESULT_RadioButton-9']"))
best_time_to_contact.select_by_visible_text("Morning")

driver.switch_to.default_content()                  #Coming out of Frame 1

#Double_Click
button = driver.find_element(By.XPATH,"//button[@ondblclick='myFunction1()']")
act = ActionChains(driver)
act.double_click(button).click().perform()
button.click()

#Drag and Drop ELEMENT
source_ele = driver.find_element(By.XPATH,"//div[@id='draggable']")
target_ele = driver.find_element(By.XPATH,"//div[@id='droppable']")

act.drag_and_drop(source_ele,target_ele).perform()

#Drag and Drop Image
#Image 1
source_ele_one = driver.find_element(By.XPATH,"//img[@alt='the peaks of high tatras']")
source_ele_two = driver.find_element(By.XPATH,"//img[@alt='the peaks of high tatras']")
target_ele_two = driver.find_element(By.XPATH,"//div[@id='trash']")

act.drag_and_drop(source_ele_one,target_ele_two).perform()

#Image 2
act = ActionChains(driver)
act.drag_and_drop(source_ele_two,target_ele_two).perform()

#Handling Sliders
initial_position = driver.find_element(By.XPATH,"//div[@id='slider']")
print(initial_position.location) #{'x': 902, 'y': 1376}

act.drag_and_drop_by_offset(initial_position,60,0).perform()

#Checking Alert Element
myalert = driver.find_element(By.XPATH,"//button[@onclick='myFunction()']")
myalert.click()
driver.switch_to.alert.accept()

#SCROLL DOWN METHOD 1: Till the element is visible:
your_age = driver.find_element(By.XPATH,"//input[@id='age']")
#driver.execute_script("arguments[0].scrollIntoView();",your_age)
#value = driver.execute_script("return window.pageOffset;")
#your_age.send_keys("23")
#print("number of pixels scrolled:",value)
#print("age bar displayed status:",your_age.is_displayed())

#SCROLL DOWN METHOD 2: By Pixel
#driver.execute_script("window.scrollBy(0,3000)","")
#value = driver.execute_script("return window.pageOffset;")
#print("number of pixels moved:",value)

#SCROLL DOWN METHOD 3:
driver.execute_script("window.scrollBy(0,document.body.scrollHeight)")
value = driver.execute_script("return window.pageYOffset;")
print("number of pixels scrolled:",value)       #2262   ----> This many pixels are moved

time.sleep(3)

#SCROLL UP METHOD 4:
driver.execute_script("window.scrollBy(0,-document.body.scrollHeight)")
value_two = driver.execute_script("return window.pageYOffset;")
print("number of pixels moved back:",value_two)


                            #EXTRACTING VALUE FROM TABLE:

#Calculating number of rows first:
table_rows = len(driver.find_elements(By.XPATH,"//table[@name='BookTable']//tr"))
table_columns = len(driver.find_elements(By.XPATH,"//table[@name='BookTable']//tr/th"))

#print("total rows:",table_rows)            #7
#print("total columns:",table_columns)      #24

#Print specific value from row and columns:
#table_row_datA = driver.find_element(By.XPATH,"//table[@name='BookTable']/tbody/tr[2]/td[1]")
#table_column_data = driver.find_element(By.XPATH,"//table[@name='BookTable']/tbody/tr[2]/td[3]")
#print(table_row_datA.text)
#print(table_column_data.text)

#Reading and Printing all data from table:
for r in range(2,table_rows+1):
    for c in range(1,table_columns+1):
        #row_column_data = driver.find_element(By.XPATH,"//table[@name='BookTable']/tbody/tr["+str(r)+"]/td["+str(c)+"]").text
        row_column_data =  driver.find_element(By.XPATH,"//table[@name='BookTable']/tbody/tr["+str(r)+"]/td["+str(c)+"]").text
        print(row_column_data,end="    ")   # end = " " will give table format
    print()                                 # print() will create new line

driver.close()


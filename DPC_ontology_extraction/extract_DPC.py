from bs4 import BeautifulSoup
import re
import spacy
nlp = spacy.load("en_core_web_sm")
def extract_content_tags(file_path):

    with open(file_path, 'r', encoding='utf-8') as file:
        html = file.read()

    soup = BeautifulSoup(html, 'html.parser')
    h3_tags = soup.find_all('h3')

    sections = []
    for i in range(len(h3_tags)):
        start = h3_tags[i]
        end = h3_tags[i + 1] if i + 1 < len(h3_tags) else None
        content = ''
        current = start.next_sibling
        while current and current != end:
            if current.name and current.name not in ['script', 'style']:
                content += current.get_text(' ', strip=True)
            current = current.next_sibling
        sections.append((start.get_text(strip=True), content.strip()))

    return sections


sections_between_h3 = extract_content_tags("./Privacy changes in Android 10  _  Android Developers.html")
# Version
def find_first_android_version(text):
    match = re.search(r'Android\s+\d+', text)
    return match.group() if match else None

# API
def find_api(text):
    matches = re.findall(r'\b[a-zA-Z_0-9]+\(\)', text)
    return list(set(matches))

# permission
def find_permission(text):
    matches = re.findall(r'\b[A-Z_]+\b', text)
    new_matches = []
    for i in matches:
        if len(i)>4:
            new_matches.append(i)
    return list(set(new_matches))

# attribute
def find_attribute(text):
    matches = re.findall(r'<[^>]*>', text)
    return list(set(matches))

# exception
def find_exception(text):
    matches = re.findall(r'\b\w*exception\w*\b', text, re.IGNORECASE)
    return list(set(matches))

#return
def extract_return(text):
    match = re.search(r'\breturns?\s+([+-]?\d+|true|false)', text, re.IGNORECASE) or re.search(r'\breturns?\s+(\w+(?:\s+\w+)?)', text, re.IGNORECASE)
    return match.group(1) if match else None

# find relationship
def app_permission_con(text,permission_list,api_list,exception_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if sentence_text.startswith("if") and \
                any(permission.lower() in sentence_text for permission in permission_list) and \
                not any(api.lower() in sentence_text for api in api_list) and \
                not any(exception.lower() in sentence_text for exception in exception_list):
            matched_sentences.append(sentence_text)

    return matched_sentences

def api_permission_con(text,permission_list,api_list,exception_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if sentence_text.startswith("if") and \
                any(permission.lower() in sentence_text for permission in permission_list) and \
                any(api.lower() in sentence_text for api in api_list) and \
                not any(exception.lower() in sentence_text for exception in exception_list):
            matched_sentences.append(sentence_text)

    return matched_sentences

def api_permission_uncon(text,permission_list,api_list,exception_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if sentence_text.startswith("to") and \
                any(permission.lower() in sentence_text for permission in permission_list) and \
                any(api.lower() in sentence_text for api in api_list) and \
                not any(exception.lower() in sentence_text for exception in exception_list):
            matched_sentences.append(sentence_text)

    return matched_sentences

def api_permission_exception(text,permission_list,api_list,exception_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if sentence_text.startswith("to") and \
                any(permission.lower() in sentence_text for permission in permission_list) and \
                any(api.lower() in sentence_text for api in api_list) and \
                any(exception.lower() in sentence_text for exception in exception_list):
            matched_sentences.append(sentence_text)

    return matched_sentences

def api_effect(text,permission_list,api_list,exception_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if sentence_text.startswith("to") and \
                not any(permission.lower() in sentence_text for permission in permission_list) and \
                any(api.lower() in sentence_text for api in api_list) and \
                not any(exception.lower() in sentence_text for exception in exception_list):
            matched_sentences.append(sentence_text)

    return matched_sentences

def api_return(text,api_list):
    doc = nlp(text)
    matched_sentences = []
    for sent in doc.sents:
        sentence_text = sent.text.strip().lower()
        if any(api.lower() in sentence_text for api in api_list) and ("return" in sentence_text or "returns" in sentence_text):
            matched_sentences.append(sentence_text)

    return matched_sentences


print("DPC Details:")
print("")
print("")
for section in sections_between_h3:
    doc = nlp(section[0])
    word_count = len([token for token in doc if token.is_alpha])
    if word_count < 4:
        continue
    print("DPC Title:" + section[0])
    print("")
    print("DPC Content:" + section[1])
    print("")
    print("entity_version: " + str(find_first_android_version(section[1])))
    print("")
    print("entity_API: " + str(find_api(section[1])))
    print("")
    print("entity_permission: " + str(find_permission(section[1])))
    print("")
    print("entity_attribute: " + str(find_attribute(section[1])))
    print("")
    print("entity_exception: " + str(find_exception(section[1])))
    print("")
    print("entity_return: " + str(extract_return(section[1])))
    print("")
    print("app_permission_relationship: " + str(app_permission_con(section[1],find_permission(section[1]),find_api(section[1]),find_exception(section[1]))))
    print("")
    print("api_permission_con_relationship: " + str(
        api_permission_con(section[1], find_permission(section[1]), find_api(section[1]), find_exception(section[1]))))
    print("")
    print("api_permission_uncon_relationship: " + str(
        api_permission_uncon(section[1], find_permission(section[1]), find_api(section[1]), find_exception(section[1]))))
    print("")
    print("api_permission_exception_relationship: " + str(
        api_permission_exception(section[1], find_permission(section[1]), find_api(section[1]),
                             find_exception(section[1]))))
    print("")
    print("api_effect_relationship: " + str(
        api_effect(section[1], find_permission(section[1]), find_api(section[1]),
                                 find_exception(section[1]))))
    print("")
    print("api_return_relationship: " + str(
        api_return(section[1],find_api(section[1]),)))

    print("*************************")

print("-------------------------------------------------------------------------------------------------")
print("-------------------------------------------------------------------------------------------------")






---
title: Docs | Index
---
# BODY HEADER

**Navigation:** `{{ navigation | raw }}`

Hello, this is the docs index page!

{% box %}
    {% columns "has-text-centered" %}
        {% column "is-4 is-offset-2" %}
Let's have some text here!
            
* A list item
* A list item
* A list item
* A list item
* A list item
        {% endcolumn %}
        
        {% column "is-4" %}
Let's have some text here!

* A list item
* A list item
* A list item
* A list item
* A list item
        {% endcolumn %}
    {% endcolumns %}
{% endbox %}


{% message "is-info" %}
    {% message_header %}
Hello, world!
    {% end_message_header %}

    {% message_body %}
Lorem ipsum dolor sit amet, consectetur adipiscing elit. **Pellentesque risus mi**, tempus quis placerat ut, porta nec nulla.
    {% end_message_body %}
{% endmessage %}

---
title: Index
---
# BODY HEADER

**Navigation:** `{{ navigation | raw }}`

Hello, this is the index page!

**Last commit: {{ lastCommit }}**

```kotlin
suspend fun main(args: Array<String>) {
    println("Hello, world!")
}
```

{% embed "blocks/info" %}
    {% block title %}Info admonition title!{% endblock %}

    {% block body %}
Some text.

* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
    {% endblock %}
{% endembed %}

<article class="message is-info">
<div class="message-header">
    <p>Info admonition title!</p>
</div>
<div class="message-body content">

* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.
* This is a Markdown list placed within a message.

</div>
</article>

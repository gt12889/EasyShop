let templateBuilder = {};

class TemplateBuilder
{
    build(templateName, value, target)
    {
        axios.get(`templates/${templateName}.html`)
            .then(response => {
                try
                {
                    const template = response.data;
                    const html = Mustache.render(template, value);
                    document.getElementById(target).innerHTML = html;
                }
                catch(e)
                {
                    console.log(e);
                }
            })
    }

    clear(target)
    {
        document.getElementById(target).innerHTML = "";
    }

    append(templateName, value, target)
    {
        axios.get(`templates/${templateName}.html`)
             .then(response => {
                 try
                 {
                     const template = response.data;
                     const html = Mustache.render(template, value);

                     const element = this.createElementFromHTML(html);
                     document.getElementById(target).appendChild(element);
                 }
                 catch(e)
                 {
                     console.log(e);
                 }
             })
    }

    createElementFromHTML(htmlString)
    {
        const div = document.createElement('div');
        div.innerHTML = htmlString.trim();

        // Change this to div.childNodes to support multiple top-level nodes.
        return div.firstChild;
    }

}

document.addEventListener('DOMContentLoaded', () => {
    templateBuilder = new TemplateBuilder();
});

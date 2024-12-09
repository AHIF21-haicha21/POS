const getAllCustomer = () => {
    const url = "customers/all";
    fetch(url)
        .then(res => {
            if(res.ok)
                return res.json();
        })
        .then(json => {
            displayCustomers(json)
        })
        .catch(err => {
            console.log(err.toString());
        })
}

const displayCustomers = (data) => {
    let content = "";
    data.forEach(d => {
        content += `<li>${d.lastName}</li>`
    })
    document.querySelector("#custList").innerHTML = content;
}
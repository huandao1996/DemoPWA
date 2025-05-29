// config PWA
self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(staticCacheName)
            .then(cache => {
                return cache.addAll(filesToCache);
            })
    );
});

self.addEventListener('fetch', event => {
    console.log('Fetch event for ', event.request.url);
    event.respondWith(
        caches.match(event.request)
            .then(response => {
                if (response) {
                    console.log('Found ', event.request.url, ' in cache');
                    return response;
                }
                console.log('Network request for ', event.request.url);
                return fetch(event.request).then(response => {
                    return caches.open(staticCacheName).then(cache => {
                        cache.put(event.request.url, response.clone());
                        return response;
                    });
                });
            }).catch(error => {
        })
    );
});

///push sự kiện
self.addEventListener('push', function(event) {
    let data = { title: 'Thông báo', body: 'Bạn có thông báo mới!' };
    if (event.data) {
        data = event.data.json();
    }
    event.waitUntil(
        self.registration.showNotification(data.title, {
            body: data.body,
            icon: 'http://a.xnimg.cn/wap/apple_icon_.png' // có thể đặt icon.png ở /static
        })
    );
});



const filesToCache= [
    '/',
    '/ui1',
    '/ui2?',
    '/ui3?'
];

const staticCacheName = 'pages-cache';

from django.template.loader import get_template
from django.template import Context
from django.http import HttpResponse
from django.shortcuts import render_to_response
from django.http import HttpRequest
from books.models import Book

import datetime

def hello(request):
    return HttpResponse("Hello world")


def current_datetime(request):
	now = datetime.datetime.now()
	#t = get_template('current_datetime.html')
	#html = t.render(Context({'current_date': now}))
	#return HttpResponse(html)

	return render_to_response('current_datetime.html', {'current_date': now})

def hours_ahead(request, offset):
	try:
		offset = int(offset)
	except ValueError:
		raise Http404()
	
	dt = datetime.datetime.now() + datetime.timedelta(hours=offset)
	html = "<html><body>In %s hour(s), it will be %s.</body></html>" % (offset, dt)
	return HttpResponse(html)

def display_meta(request):
	values = request.META.items()
	values.sort()
	return render_to_response('meta.html',{'listMeta':values})


def search_form(request):
	return render_to_response('search_form.html')


def search(request):
	error = False
	if 'q' in request.GET and request.GET['q']:
		q = request.GET['q']
		if not q:
			error = True
		else :
			books = Book.objects.filter(title__icontains = q)
			return render_to_response('search_result.html', {'books': books, 'query': q})
		return render_to_response('search_result.html',{'error':error})
	else:
		return HttpResponse('Please submit a search term.')
